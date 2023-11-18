package ftn.isa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.isa.domain.CompanyAdmin;
import ftn.isa.repository.CompanyAdminRepository;

@Service
public class CompanyAdminService {
	@Autowired
    CompanyAdminRepository cARepository;
	
    public List<CompanyAdmin> findAll() {
        return cARepository.findAll();
    }
    public CompanyAdmin save(CompanyAdmin user) {
        return cARepository.save(user);
    }

    public CompanyAdmin findOne(int id){ return cARepository.findById(id).orElseGet(null); }
}
