package ftn.isa.repository;

import java.util.List;

import ftn.isa.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import ftn.isa.domain.Appointment;
import org.springframework.data.jpa.repository.Query;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    //@Query("select e from Equipment e join fetch e.company c where e.id =?1")
    @Query("select a from Appointment a join fetch a.admin ca where ca.company.id =?1")
    public List<Appointment> findAppointmentByCompany(Integer companyId);
}
