package ftn.isa.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.domain.Appointment;
import ftn.isa.repository.AppointmentRepository;



@Service
public class AppointmentService {
	@Autowired
    AppointmentRepository aRepository;
	
	public List<Appointment> findAll() {
        return aRepository.findAll();
    }
    public Appointment save(Appointment a) {
        return aRepository.save(a);
    }
    public Appointment getById(int id) {
		return aRepository.getReferenceById(id);
	}
    public List<Appointment> findByCompanyId(Integer companyId){
        return aRepository.findAppointmentByCompany(companyId);
    }
    public List<Appointment> findByUserId(Integer userId){
        return aRepository.findAppointmentByUserId(userId);
    }
}
