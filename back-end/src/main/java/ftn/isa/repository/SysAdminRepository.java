package ftn.isa.repository;

import ftn.isa.domain.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import ftn.isa.domain.CompanyAdmin;

public interface SysAdminRepository extends JpaRepository<SystemAdmin, Integer> {

}
