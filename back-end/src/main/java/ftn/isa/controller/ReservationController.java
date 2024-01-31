package ftn.isa.controller;

import ftn.isa.domain.*;
import ftn.isa.dto.*;
import ftn.isa.service.EmailService;
import ftn.isa.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            totalAmount += reservationDTO.getAmounts().get(i);
        }
        ReservationResponseDTO responseDTO = new ReservationResponseDTO();
        responseDTO.setId(reservation.getId());
        responseDTO.setStatus(reservation.getStatus());
        responseDTO.setAppointmentId(reservation.getAppointment().getId());
        responseDTO.setUserId(reservation.getRegisteredUser().getId());
        //String data = "Time: "+reservation.getAppointment().getDateTime()+", equipment amount: " +totalAmount+", receiver: "+reservation.getRegisteredUser().getFirstName()+" "+reservation.getRegisteredUser().getLastName()+
        //        ",\n company: "+reservation.getAppointment().getAdmin().getCompany().getName()+", company admin: "+reservation.getAppointment().getAdmin().getFirstName()+" "+reservation.getAppointment().getAdmin().getLastName();
        String data = reservation.getId().toString();
        emailService.sendReservationConfirmationQR(data, reservation.getRegisteredUser().getEmail());
        reservation.setQrcode(data);
        reservationService.update(reservation);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping(value = "/cancelReservation")
    public ResponseEntity<Integer> cancelReservation(@RequestBody ReservationCancelDTO cancelDTO) {
        int penalPoints = reservationService.cancelReservation(cancelDTO);
        return new ResponseEntity<>(penalPoints, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    @PutMapping(value = "/pickupReservation")
    public ResponseEntity<Integer> pickupReservation(@RequestBody ReservationCancelDTO cancelDTO) {
        int penalPoints = reservationService.cancelReservation(cancelDTO);
        return new ResponseEntity<>(penalPoints, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    @GetMapping(value = "/users/{companyId}")
    public ResponseEntity<List<UserResponseDTO>> getUsersWithReservationsInCompany(@PathVariable Integer companyId) {
        HashSet<RegisteredUser> reservations = reservationService.getUsersWithReservationsInCompany(companyId);
        List<UserResponseDTO> res = new ArrayList<>();
        for (RegisteredUser c : reservations) {
            res.add(new UserResponseDTO(c));
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    @GetMapping(value = "/futureReservations/{adminsId}")
    public ResponseEntity<List<ReservationDTO>> getFutureReservationsForAdmin(@PathVariable Integer adminsId) {
        List<Reservation> reservations = reservationService.getReservationsForAdmin(adminsId);
        List<ReservationDTO> res = new ArrayList<>();
        for (Reservation r : reservations) {
            if(r.getAppointment().getDateTime().isAfter(LocalDateTime.now()) && r.getStatus() != ReservationStatus.Cancelled){
                res.add(new ReservationDTO(r));
            }
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    @GetMapping(value = "/pastReservations/{adminsId}")
    public ResponseEntity<List<ReservationDTO>> getPastOrCanceledReservationsForAdmin(@PathVariable Integer adminsId) {
        List<Reservation> reservations = reservationService.getReservationsForAdmin(adminsId);
        List<ReservationDTO> res = new ArrayList<>();
        for (Reservation r : reservations) {
            if(r.getAppointment().getDateTime().isBefore(LocalDateTime.now())){
                res.add(new ReservationDTO(r));
            }
            if(r.getStatus() == ReservationStatus.Cancelled){
                res.add(new ReservationDTO(r));
            }
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    @PutMapping(value = "/setReservationDone")
    public ResponseEntity<ReservationResponseDTO> setReservationDone(@RequestBody ReservationDTO resDTO) throws InterruptedException {
        Reservation reservation = reservationService.setReservationDone(resDTO.getId());
        ReservationResponseDTO responseDTO = new ReservationResponseDTO(reservation);
        String data = "Congratulations, your reservation pickup was confirmed by "+reservation.getAppointment().getAdmin().getFirstName()+" "+reservation.getAppointment().getAdmin().getLastName() +"!";
        emailService.sendPickupConfirmationMail(reservation.getRegisteredUser(), data);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    @GetMapping(value = "/equipment/{resId}")
    public ResponseEntity<List<ResEquipmentDTO>> getEquipmentForReservation(@PathVariable Integer resId) {
        Set<ReservationItem> equipment = reservationService.getEquipmentByReservation(resId);
        List<ResEquipmentDTO> res = new ArrayList<>();
        for (ReservationItem e : equipment) {
            res.add(new ResEquipmentDTO(e.getEquipment().getName(), e.getAmount()));
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
