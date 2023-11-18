package ftn.isa.repository;

import ftn.isa.domain.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredUserRepository  extends JpaRepository<RegisteredUser, Integer> {

}
