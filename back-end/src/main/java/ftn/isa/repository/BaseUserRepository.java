package ftn.isa.repository;

import ftn.isa.domain.BaseUser;
import ftn.isa.domain.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseUserRepository extends JpaRepository<BaseUser, Integer> {
    public BaseUser findByUsername(String username);
}
