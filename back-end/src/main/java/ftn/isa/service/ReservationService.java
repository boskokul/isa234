package ftn.isa.service;

import ftn.isa.domain.*;
import ftn.isa.dto.EquipmentDTO;
import ftn.isa.dto.ExtraordinaryAppointmentDTO;
import ftn.isa.dto.ReservationCancelDTO;
import ftn.isa.dto.ReservationCreateDTO;
import ftn.isa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
@Transactional
public class ReservationService {
    @Autowired
    RegisteredUserRepository registeredUserRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    EquipmentRepository equipmentRepository;
    @Autowired
    ReservationItemRepository reservationItemRepository;

    @Transactional(readOnly = false)
    public Reservation makeReservation(ReservationCreateDTO reservationDTO){
        List<Reservation> reservations = reservationRepository.findByAppointmentId(reservationDTO.getAppointmentId());
        for(Reservation r: reservations){
            if(r.getStatus() == ReservationStatus.NotFinalized){
                return null;
            }
        }
        for(int i = 0; i < reservationDTO.getAmounts().size(); i++){
            Equipment e = equipmentRepository.findByIdLocked(reservationDTO.getEquipmentIds().get(i));
            if(e.getFreeAmount() < reservationDTO.getAmounts().get(i)){
                return null;
            }
        }
        Reservation reservation = new Reservation();
        reservation.setStatus(ReservationStatus.NotFinalized);
        reservation.setRegisteredUser(registeredUserRepository.getReferenceById(reservationDTO.getUserId()));
        reservation.setAppointment(appointmentRepository.getReferenceById(reservationDTO.getAppointmentId()));
        reservation = reservationRepository.save(reservation);
        int numberOfEquipments = reservationDTO.getEquipmentIds().size();
        for(int i=0; i<numberOfEquipments; i++){
            makeReservationItem(reservation.getId(), reservationDTO.getEquipmentIds().get(i), reservationDTO.getAmounts().get(i));
        }
        return reservation;
    }
    public ReservationItem makeReservationItem(int reservationId, int equipmentId, int amount){
        ReservationItem reservationItem = new ReservationItem();
        reservationItem.setAmount(amount);
        reservationItem.setReservation(reservationRepository.getReferenceById(reservationId));
        Equipment equipment = equipmentRepository.findByIdLocked(equipmentId);
        equipment.setFreeAmount(equipment.getFreeAmount() - amount);
        equipment.setReservedAmount(equipment.getReservedAmount() + amount);
        equipmentRepository.save(equipment);
        reservationItem.setEquipment(equipment);
        return reservationItemRepository.save(reservationItem);
    }
    public int cancelReservation(ReservationCancelDTO cancelDTO){
        //nemam validaciju da li je korisnik zaista rezervisao taj termin
        Reservation reservation = reservationRepository.findByAppointmentIdAndRegisteredUserId(cancelDTO.getAppointmentId(), cancelDTO.getUserId()).get(0);
        reservation.setStatus(ReservationStatus.Cancelled);
        reservationRepository.save(reservation);
        Appointment appointment = appointmentRepository.getReferenceById(cancelDTO.getAppointmentId());
        RegisteredUser user = registeredUserRepository.getReferenceById(cancelDTO.getUserId());
        long x = calculateHoursDifference(LocalDateTime.of(appointment.getDateTime().getYear(), appointment.getDateTime().getMonth(), appointment.getDateTime().getDayOfMonth(), appointment.getDateTime().getHour(), 0), LocalDateTime.now());
        if(x < 24){
            user.setPenalPoints(user.getPenalPoints()+2);
        }
        else{
            user.setPenalPoints(user.getPenalPoints()+1);
        }
        registeredUserRepository.save(user);
        updateEquipmentAmount(reservation.getId());
        if(appointment.isExtraordinary() != null && appointment.isExtraordinary()){
            List<ReservationItem> reservationItems = reservationItemRepository.findByReservationId(reservation.getId());
            reservationItemRepository.deleteAll(reservationItems);
            reservationRepository.delete(reservation);
            appointmentRepository.delete(appointment);
        }
        return user.getPenalPoints();
    }
    private void updateEquipmentAmount(Integer reservationId){
        List<ReservationItem> reservationItems =  reservationItemRepository.findByReservationId(reservationId);
        for(ReservationItem item: reservationItems){
            Equipment equipment = item.getEquipment();
            equipment.setReservedAmount(equipment.getReservedAmount() - item.getAmount());
            equipment.setFreeAmount(equipment.getFreeAmount() + item.getAmount());
            equipmentRepository.save(equipment);
        }
    }
    private long calculateHoursDifference(LocalDateTime currentDateTime, LocalDateTime appointmentDateTime) {
        Duration duration = Duration.between(appointmentDateTime, currentDateTime);
        long hoursDifference = duration.toHours();
        return hoursDifference;
    }
    public List<Reservation> getReservationsByAppointmentId(int id){
        return reservationRepository.findByAppointmentId(id);
    }

    public Set<ReservationItem> getEquipmentByReservation(int id){
        Set<ReservationItem> eq = new HashSet<>();
        Reservation r = reservationRepository.getReferenceById(id);
        return r.getReservationItems();
    }

    public HashSet<RegisteredUser> getUsersWithReservationsInCompany(int id){
        HashSet<RegisteredUser> res = new HashSet<>();
        for(Reservation r : reservationRepository.findAllByCompany(id)){
            res.add(r.getRegisteredUser());
        }
        return res;
    }

    public List<Reservation> getReservationsForAdmin(int id){
        return reservationRepository.findAllByAdminsId(id);
    }
    public boolean isAvailable(int appointmentId, int userId){
        List<Reservation> reservations = reservationRepository.findByAppointmentId(appointmentId);
        if(reservations.isEmpty())
            return true;
        for(Reservation r: reservations){
            if(r.getStatus() != ReservationStatus.Cancelled)
                return false;
        }
        return reservationRepository.findByAppointmentIdAndRegisteredUserId(appointmentId, userId).isEmpty();
    }

    @Transactional
    public Reservation setReservationDone(Integer resId){
        Reservation reservation = reservationRepository.getReferenceById(resId);
        reservation.setStatus(ReservationStatus.Finalized);
        reservationRepository.save(reservation);
        updateEquipmentAfterDone(reservation.getId());
        return reservation;
    }
    private void updateEquipmentAfterDone(Integer reservationId){
        List<ReservationItem> reservationItems =  reservationItemRepository.findByReservationId(reservationId);
        for(ReservationItem item: reservationItems){
            Equipment equipment = item.getEquipment();
            equipment.setReservedAmount(equipment.getReservedAmount() - item.getAmount());
            equipmentRepository.save(equipment); // promeniti save
        }
    }

    public Reservation update(Reservation updateReservation){
        return reservationRepository.save(updateReservation);
    }
}
