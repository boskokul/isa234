package ftn.isa.service;

import ftn.isa.domain.*;
import ftn.isa.dto.ExtraordinaryAppointmentDTO;
import ftn.isa.dto.ReservationCreateDTO;
import ftn.isa.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
