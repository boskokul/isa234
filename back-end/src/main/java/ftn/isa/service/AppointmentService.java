package ftn.isa.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ftn.isa.domain.Company;
import ftn.isa.domain.CompanyAdmin;
import ftn.isa.dto.ExtraordinaryAppointmentDTO;
import ftn.isa.repository.CompanyAdminRepository;
import ftn.isa.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.domain.Appointment;
import ftn.isa.repository.AppointmentRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AppointmentService {
	@Autowired
    AppointmentRepository aRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CompanyAdminRepository companyAdminRepository;
	
	public List<Appointment> findAll() {
        return aRepository.findAll();
    }
    @Transactional(readOnly = false)
    public Appointment save(Appointment appointment) {
        for(Appointment a: aRepository.findAppointmentByCompany(appointment.getAdmin().getCompany().getId())){
            if(a.getDateTime().isAfter(appointment.getDateTime()) && a.getDateTime().isBefore(appointment.getDateTime().plusMinutes(30))
            || a.getDateTime().plusMinutes(30).isAfter(appointment.getDateTime()) && a.getDateTime().plusMinutes(30).isBefore(appointment.getDateTime().plusMinutes(30))
            ){
                return null;
            }
        }
        return aRepository.save(appointment); // lock za save
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
    @Transactional(readOnly = true)
    public List<ExtraordinaryAppointmentDTO> getExtraordinaryAppointments(LocalDate date, int companyId){
        List<ExtraordinaryAppointmentDTO> ret = getAllPosslibleExtraotdinaryAppointments(date, companyId);
        List<Appointment> scheduledAppointments = aRepository.findAppointmentByCompanyAndDate(companyId, LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(date, LocalTime.MAX));

        var company = companyRepository.getById(companyId);
        List<CompanyAdmin> companyAdmins = new ArrayList<>(company.getCompanyAdmins());
        List<ExtraordinaryAppointmentDTO> forRemoval = new ArrayList<>();
        for(ExtraordinaryAppointmentDTO eoAppointment: ret){
            List<Integer> freeAdmins = new ArrayList<>(companyAdmins.stream().map(CompanyAdmin::getId).collect(Collectors.toList()));
            for(Appointment scheduled: scheduledAppointments){
                if(eoAppointment.getDateTime().plusMinutes(eoAppointment.getDuration()).isBefore(scheduled.getDateTime())
                || eoAppointment.getDateTime().isAfter(scheduled.getDateTime().plusMinutes(scheduled.getDuration()))){
                    continue;
                }
                freeAdmins.removeIf(ca -> scheduled.getAdmin().getId()==ca);
            }
            if(freeAdmins.size() == 0){
                forRemoval.add(eoAppointment);
            } else{
                eoAppointment.setAdminsId(freeAdmins.get(0));
                CompanyAdmin ca = null;
                for(CompanyAdmin companyAdmin: companyAdmins){
                    if(companyAdmin.getId() == freeAdmins.get(0)){
                        ca = companyAdmin;
                        break;
                    }
                }
                eoAppointment.setAdminName(ca.getFirstName() + " " + ca.getLastName());
            }
        }
        ret.removeAll(forRemoval);
        return ret;
    }

    @Transactional
    public Appointment CreateExtraordinaryAppointment(ExtraordinaryAppointmentDTO eoAppointment, int compnayId){
        if(!IsExtraordinaryAppointmentValid(eoAppointment, compnayId)){
            return null;
        }
        Appointment appointment = new Appointment();
        appointment.setReservation(null);
        appointment.setDateTime(eoAppointment.getDateTime());
        appointment.setDuration(eoAppointment.getDuration());
        appointment.setAdmin(companyAdminRepository.getReferenceById(eoAppointment.getAdminsId()));
        return aRepository.save(appointment); // dodati lockSave
    }

    private List<ExtraordinaryAppointmentDTO> getAllPosslibleExtraotdinaryAppointments(LocalDate date, int companyId){
        int duration = 30;
        List<ExtraordinaryAppointmentDTO> ret = new ArrayList<>();
        Optional<Company> company = companyRepository.findById(companyId);
        var startTime = company.get().getStartTime();
        var endTime = company.get().getEndTime();
        for(LocalTime time = startTime; time.isBefore(endTime.minusMinutes(duration-1)); time = time.plusMinutes(duration)){
            LocalDateTime dateTime = LocalDateTime.of(date, time);
            ret.add(
                    new ExtraordinaryAppointmentDTO(0, "Pera", dateTime, duration)
            );
        }
        return ret;
    }

    @Transactional(readOnly = true)
    public boolean IsExtraordinaryAppointmentValid(ExtraordinaryAppointmentDTO eoAppointment, int companyId){
        LocalDate date = eoAppointment.getDateTime().toLocalDate();
        List<Appointment> scheduledAppointments = aRepository.findAppointmentByCompanyAndDate(companyId, LocalDateTime.of(date, LocalTime.MIN), LocalDateTime.of(date, LocalTime.MAX));
        for(Appointment scheduled: scheduledAppointments){
            if(scheduled.getAdmin().getId() != eoAppointment.getAdminsId()){
                continue;
            }
            if(eoAppointment.getDateTime().plusMinutes(eoAppointment.getDuration()).isBefore(scheduled.getDateTime())
            || eoAppointment.getDateTime().isAfter(scheduled.getDateTime().plusMinutes(scheduled.getDuration()))){
                continue;
            }
            return false;
        }
        return true;
    }
}
