package ftn.isa.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.domain.Appointment;
import ftn.isa.domain.Company;
import ftn.isa.dto.AppointmentCreateDTO;
import ftn.isa.dto.AppointmentResponseDTO;
import ftn.isa.dto.CompanyResponseDTO;
import ftn.isa.service.AppointmentService;
import ftn.isa.service.CompanyAdminService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/appointments")
public class AppointmentController {
	@Autowired
    private AppointmentService appointmentService;
	@Autowired
    private CompanyAdminService cAdminService;
	
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


        AppointmentResponseDTO aResponseDTO = new AppointmentResponseDTO(a);
        return new ResponseEntity<>(aResponseDTO, HttpStatus.OK);
    }
	
	@GetMapping(value = "/{id}")
    public ResponseEntity<AppointmentResponseDTO> getById(@PathVariable Integer id) {
		Appointment a = appointmentService.getById(id);
        AppointmentResponseDTO aResponseDTO = new AppointmentResponseDTO(a);

        return new ResponseEntity<>(aResponseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/company/{id}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByCompanyId(@PathVariable Integer id) {
        List<Appointment> appointments = appointmentService.findByCompanyId(id);
        List<AppointmentResponseDTO> aResponseDTOs = new ArrayList<>();
        for (Appointment c : appointments) {
            if(c.getDateTime().isBefore(LocalDateTime.now())){
                continue;
            }
            else if(c.getReservation() != null){
                continue;
            }
            aResponseDTOs.add(new AppointmentResponseDTO(c));
        }
        return new ResponseEntity<>(aResponseDTOs, HttpStatus.OK);
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


}
