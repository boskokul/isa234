package ftn.isa.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ftn.isa.domain.*;
import ftn.isa.dto.*;
import ftn.isa.service.EmailService;
import ftn.isa.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ftn.isa.service.AppointmentService;
import ftn.isa.service.CompanyAdminService;

import javax.mail.MessagingException;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/appointments")
public class AppointmentController {
	@Autowired
    private AppointmentService appointmentService;
	@Autowired
    private CompanyAdminService cAdminService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private EmailService emailService;

	@GetMapping(value = "/all")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointments() {
        List<Appointment> appointments = appointmentService.findAll();

        List<AppointmentResponseDTO> aResponseDTOs = new ArrayList<>();
        for (Appointment c : appointments) {
        	aResponseDTOs.add(new AppointmentResponseDTO(c));
        }

        return new ResponseEntity<>(aResponseDTOs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('COMPANY_ADMIN')")
	@PostMapping(consumes = "application/json")
    public ResponseEntity<AppointmentResponseDTO> save(@RequestBody AppointmentCreateDTO aDTO){
        Appointment a = new Appointment();
        a.setDateTime(aDTO.getDateTime());
        a.setDuration(aDTO.getDuration());
        a.setAdmin(cAdminService.findOne(aDTO.getAdminsId()));
        a = appointmentService.save(a);
        if(a == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        AppointmentResponseDTO aResponseDTO = new AppointmentResponseDTO(a);
        return new ResponseEntity<>(aResponseDTO, HttpStatus.OK);
    }
	
	@GetMapping(value = "/{id}")
    public ResponseEntity<AppointmentResponseDTO> getById(@PathVariable Integer id) {
		Appointment a = appointmentService.getById(id);
        AppointmentResponseDTO aResponseDTO = new AppointmentResponseDTO(a);

        return new ResponseEntity<>(aResponseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/company/{id}/{userId}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByCompanyId(@PathVariable Integer id,@PathVariable Integer userId) {
        List<Appointment> appointments = appointmentService.findByCompanyId(id);
        List<AppointmentResponseDTO> aResponseDTOs = new ArrayList<>();
        for (Appointment c : appointments) {
            if(c.getDateTime().isBefore(LocalDateTime.now())){
                continue;
            }
            else if(!reservationService.isAvailable(c.getId(), userId)){
                continue;
            }
            aResponseDTOs.add(new AppointmentResponseDTO(c));
        }
        return new ResponseEntity<>(aResponseDTOs, HttpStatus.OK);
    }
    @GetMapping(value = "/futureappointments/{id}")
    public ResponseEntity<List<AppointmentInfoDTO>> getFutureAppointments(@PathVariable Integer id) {
        List<Appointment> appointments = appointmentService.findByUserId(id);
        List<AppointmentInfoDTO> aResponseDTOs = new ArrayList<>();
        for(Appointment a : appointments){
            if(a.getDateTime().isBefore(LocalDateTime.now())){
                continue;
            }
            if(a.getReservation().getStatus() == ReservationStatus.Cancelled){
                continue;
            }
            aResponseDTOs.add(new AppointmentInfoDTO(a));
        }
        return new ResponseEntity<>(aResponseDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/pastAppointments/{id}")
    public ResponseEntity<List<AppointmentResponseDTO>> getPastAppointments(@PathVariable Integer id) {
        List<Appointment> appointments = appointmentService.findByUserId(id);
        List<AppointmentResponseDTO> aResponseDTOs = new ArrayList<>();
        for(Appointment a : appointments){
            if(a.getReservation().getStatus() != ReservationStatus.Finalized){
                continue;
            }
            aResponseDTOs.add(new AppointmentResponseDTO(a));
        }
        return new ResponseEntity<>(aResponseDTOs, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('REGISTERED_USER')")
    @GetMapping(value = "/extraordinaryAppointments")
    public ResponseEntity<List<ExtraordinaryAppointmentDTO>> getExtraordinaryAppointments(@RequestParam("date") String date, @RequestParam("companyId") int companyId){
        var yearMonthDay = date.split("-");
        var list = appointmentService.getExtraordinaryAppointments(LocalDate.of(Integer.parseInt(yearMonthDay[0]), Integer.parseInt(yearMonthDay[1]), Integer.parseInt(yearMonthDay[2])), companyId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('REGISTERED_USER')")
    @PostMapping(value = "/selectExtraordinaryAppointment")
    public ResponseEntity<AppointmentResponseDTO> selectExtraordinaryAppointment(@RequestBody ExtraordinaryAppointmentCreateDTO extraordinaryAppointmentCreateDTO) throws MessagingException {
        ExtraordinaryAppointmentDTO eoAppointment = extraordinaryAppointmentCreateDTO.getExtraordinaryAppointmentDTO();
        ReservationCreateDTO reservationDTO = extraordinaryAppointmentCreateDTO.getReservationCreateDTO();
        int companyId = extraordinaryAppointmentCreateDTO.getCompanyId();
        Appointment appointment = appointmentService.CreateExtraordinaryAppointment(eoAppointment, companyId);
        if(appointment == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        reservationDTO.setAppointmentId(appointment.getId());
        Reservation reservation = reservationService.makeReservation(reservationDTO);
        if(reservation == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        int numberOfEquipments = reservationDTO.getEquipmentIds().size();
        int totalAmount = 0;
        for(int i=0; i<numberOfEquipments; i++){
            totalAmount += reservationDTO.getAmounts().get(i);
        }
        appointment.setReservation(reservation);
        appointment.setExtraordinary(true);
        appointment = appointmentService.save(appointment);

        //String data = "You created appointment at time: "+appointment.getDateTime()+", equipment amount: " +totalAmount+", company admin: "+appointment.getAdmin().getFirstName()+" "+reservation.getAppointment().getAdmin().getLastName();
        String data = reservation.getId().toString();
        emailService.sendReservationConfirmationQR(data, reservation.getRegisteredUser().getEmail());

        AppointmentResponseDTO responseDTO = new AppointmentResponseDTO(
                appointment.getId(),
                appointment.getAdmin().getFirstName() + " " + appointment.getAdmin().getLastName(),
                appointment.getDateTime(),
                appointment.getDuration()
        );
        reservation.setQrcode(data);
        reservationService.update(reservation);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/company/full/{id}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByCompanyIdAll(@PathVariable Integer id) {
        List<Appointment> appointments = appointmentService.findByCompanyId(id);
        List<AppointmentResponseDTO> aResponseDTOs = new ArrayList<>();
        for (Appointment c : appointments) {
            if(c.getDateTime().isBefore(LocalDateTime.now())){
                continue;
            }
            aResponseDTOs.add(new AppointmentResponseDTO(c));
        }
        return new ResponseEntity<>(aResponseDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/reservationUser/{id}")
    public ResponseEntity<ResAppDTO> getReservationUserByAppointmentId(@PathVariable Integer id) {
        /*Appointment a = appointmentService.getById(id);
        ResAppDTO resAppDTO = new ResAppDTO();
        String ret = "";
        if(a.getReservation() == null){
            ret = "";
        } else{
            RegisteredUser user = a.getReservation().getRegisteredUser();
            ret = user.getFirstName() + " " + user.getLastName();
        }
        resAppDTO.setFullName(ret);*/
        String ret = "";
        ResAppDTO resAppDTO = new ResAppDTO();
        List<Reservation> reservations = reservationService.getReservationsByAppointmentId(id);
        if(reservations.isEmpty()){
            ret = "";
        }
        else{
            for(Reservation r: reservations){
                if(r.getStatus() != ReservationStatus.Cancelled){
                    RegisteredUser user = r.getRegisteredUser();
                    ret = user.getFirstName() + " " + user.getLastName();
                    break;
                }
            }
        }
        resAppDTO.setFullName(ret);
        return new ResponseEntity<>(resAppDTO, HttpStatus.OK);
    }

}
