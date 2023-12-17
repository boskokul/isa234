package ftn.isa.repository;

import ftn.isa.domain.ReservationItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationItemRepository extends JpaRepository<ReservationItem, Integer> {
}
