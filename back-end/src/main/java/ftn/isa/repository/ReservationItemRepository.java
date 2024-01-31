package ftn.isa.repository;

import ftn.isa.domain.ReservationItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationItemRepository extends JpaRepository<ReservationItem, Integer> {
    List<ReservationItem> findByReservationId(int reservationId);
}
