package ftn.isa.repository;

import ftn.isa.domain.Reservation;
import ftn.isa.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    public List<Reservation> findByAppointmentId(int appointmentId);
    @Query("select r from Reservation r join fetch r.appointment a join fetch a.admin ca where ca.company.id=?1")
    public List<Reservation> findAllByCompany(Integer companyId);

    public List<Reservation> findByAppointmentIdAndRegisteredUserId(int appointmentId, int userId);
}
