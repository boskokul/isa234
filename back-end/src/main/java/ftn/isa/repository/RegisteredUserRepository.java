package ftn.isa.repository;

import ftn.isa.domain.RegisteredUser;
import ftn.isa.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredUserRepository  extends JpaRepository<RegisteredUser, Integer> {
    public RegisteredUser findOneByEmail(String email);
}
