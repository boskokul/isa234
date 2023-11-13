package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.isa.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
