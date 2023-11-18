package ftn.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.domain.Company;
import ftn.isa.repository.CompanyRepository;

@Service
public class CompanyService {
	@Autowired
	CompanyRepository companyRepository;
	
	public List<Company> findAll(){
		return companyRepository.findAll();
	}
	
	public Company getById(int id) {
		return companyRepository.getReferenceById(id);
	}
	
	public Company save(Company c) {
		return companyRepository.save(c);
	}
	public Company findOne(Integer id) {
		return companyRepository.findById(id).orElseGet(null);
	}

	public Company Update(Company c) {
		companyRepository.deleteById(c.getId());
		return companyRepository.save(c);
	}

	public Integer getLastCompanyId() {
		List<Company> companies = companyRepository.findAll();
		if (!companies.isEmpty()) {
			Company lastCompany = companies.get(companies.size() - 1);
			return lastCompany.getId();
		}
		return null;
	}

	public Company findOneWithEquipment(Integer companyId) {
		return companyRepository.findOneWithEquipment(companyId);
	}
}
