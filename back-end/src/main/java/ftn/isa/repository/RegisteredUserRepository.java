package ftn.isa.repository;

import ftn.isa.domain.BaseUser;
import ftn.isa.domain.RegisteredUser;
import ftn.isa.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisteredUserRepository  extends JpaRepository<RegisteredUser, Integer> {
    public RegisteredUser findByUsername(String username);

    public List<RegisteredUser> findAllByPenalPointsGreaterThan(Integer value);
}
