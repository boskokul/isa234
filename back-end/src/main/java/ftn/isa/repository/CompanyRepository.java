package ftn.isa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ftn.isa.domain.Company;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	@Query("select c from Company c join fetch c.equipment e where c.id =?1")
	public Company findOneWithEquipment(Integer companyId);
	public List<Company> findByNameContainsIgnoreCaseAndCountryContainingIgnoreCaseAndCityContainsIgnoreCase(String name, String country, String city);
}
