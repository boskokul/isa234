package ftn.isa.controller;

import ftn.isa.domain.Appointment;
import ftn.isa.domain.Reservation;
import ftn.isa.dto.AppointmentResponseDTO;
import ftn.isa.dto.ReservationCreateDTO;
import ftn.isa.dto.ReservationResponseDTO;
import ftn.isa.service.EmailService;
import ftn.isa.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/reservation")
public class ReservationController {
    @Autowired
    ReservationService reservationService;
    @Autowired
    EmailService emailService;
    @PreAuthorize("hasRole('REGISTERED_USER')")
    @PostMapping
    public ResponseEntity<ReservationResponseDTO> makeReservation(@RequestBody ReservationCreateDTO reservationDTO) throws MessagingException {
        Reservation reservation = reservationService.makeReservation(reservationDTO);
        int numberOfEquipments = reservationDTO.getEquipmentIds().size();
        int totalAmount = 0;
        for(int i=0; i<numberOfEquipments; i++){
            reservationService.makeReservationItem(reservation.getId(), reservationDTO.getEquipmentIds().get(i), reservationDTO.getAmounts().get(i));
            totalAmount += reservationDTO.getAmounts().get(i);
        }
        ReservationResponseDTO responseDTO = new ReservationResponseDTO();
        responseDTO.setId(reservation.getId());
        responseDTO.setStatus(reservation.getStatus());
        responseDTO.setAppointmentId(reservation.getAppointment().getId());
        responseDTO.setUserId(reservation.getRegisteredUser().getId());
        String data = "Time: "+reservation.getAppointment().getDateTime()+", equipment amount: " +totalAmount+", company admin: "+reservation.getAppointment().getAdmin().getFirstName()+" "+reservation.getAppointment().getAdmin().getLastName();
        emailService.sendReservationConfirmationQR(data, reservation.getRegisteredUser().getEmail());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
