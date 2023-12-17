package ftn.isa.repository;

import java.time.LocalDateTime;
import java.util.List;

import ftn.isa.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import ftn.isa.domain.Appointment;
import org.springframework.data.jpa.repository.Query;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    //@Query("select e from Equipment e join fetch e.company c where e.id =?1")
    @Query("select a from Appointment a join fetch a.admin ca where ca.company.id =?1")
    public List<Appointment> findAppointmentByCompany(Integer companyId);

    @Query("select a from Appointment a join fetch a.admin ca where ca.company.id=?1 and a.dateTime>?2 and a.dateTime<?3")
    public List<Appointment> findAppointmentByCompanyAndDate(Integer companyId, LocalDateTime start, LocalDateTime end);
}
