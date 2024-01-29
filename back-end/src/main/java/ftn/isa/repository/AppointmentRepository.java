package ftn.isa.repository;

import java.time.LocalDateTime;
import java.util.List;

import ftn.isa.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import ftn.isa.domain.Appointment;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from Appointment a join fetch a.admin ca where ca.company.id =?1")
    public List<Appointment> findAppointmentByCompany(Integer companyId);
    @Query("select a from Appointment a join fetch a.reservation r where r.registeredUser.id =?1")
    public List<Appointment> findAppointmentByUserId(Integer userId);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select a from Appointment a join fetch a.admin ca where ca.company.id=?1 and a.dateTime>?2 and a.dateTime<?3")
    public List<Appointment> findAppointmentByCompanyAndDate(Integer companyId, LocalDateTime start, LocalDateTime end);
}
