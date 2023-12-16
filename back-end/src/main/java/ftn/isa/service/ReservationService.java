package ftn.isa.service;

import ftn.isa.domain.Equipment;
import ftn.isa.domain.Reservation;
import ftn.isa.domain.ReservationItem;
import ftn.isa.domain.ReservationStatus;
import ftn.isa.dto.ReservationCreateDTO;
import ftn.isa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
    public Reservation makeReservation(ReservationCreateDTO reservationDTO){
        Reservation reservation = new Reservation();
        reservation.setStatus(ReservationStatus.NotFinalized);
        reservation.setRegisteredUser(registeredUserRepository.getReferenceById(reservationDTO.getUserId()));
        reservation.setAppointment(appointmentRepository.getReferenceById(reservationDTO.getAppointmentId()));
        return reservationRepository.save(reservation);
    }
    public ReservationItem makeReservationItem(int reservationId, int equipmentId, int amount){
        ReservationItem reservationItem = new ReservationItem();
        reservationItem.setAmount(amount);
        reservationItem.setReservation(reservationRepository.getReferenceById(reservationId));
        Equipment equipment = equipmentRepository.getReferenceById(equipmentId);
        equipment.setFreeAmount(equipment.getFreeAmount() - amount);
        equipment.setReservedAmount(equipment.getReservedAmount() + amount);
        equipmentRepository.save(equipment);
        reservationItem.setEquipment(equipment);
        return reservationItemRepository.save(reservationItem);
    }
}
