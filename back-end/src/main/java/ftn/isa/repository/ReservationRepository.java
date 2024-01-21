package ftn.isa.repository;

import ftn.isa.domain.Reservation;
import ftn.isa.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    public List<Reservation> findByAppointmentId(int appointmentId);
    public List<Reservation> findByAppointmentIdAndRegisteredUserId(int appointmentId, int userId);
}
