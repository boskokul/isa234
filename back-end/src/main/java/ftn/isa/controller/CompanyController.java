package ftn.isa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ftn.isa.domain.Company;
import ftn.isa.domain.Equipment;
import ftn.isa.dto.CompanyResponseDTO;
import ftn.isa.dto.EquipmentDTO;
import ftn.isa.service.CompanyService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/companies")
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	@GetMapping
    public ResponseEntity<List<CompanyResponseDTO>> getCompanies() {
        List<Company> companies = companyService.findAll();

        List<CompanyResponseDTO> companyResponseDTOs = new ArrayList<>();
        for (Company c : companies) {
        	companyResponseDTOs.add(new CompanyResponseDTO(c));
        }

        return new ResponseEntity<>(companyResponseDTOs, HttpStatus.OK);
    }
	@RequestMapping(value = "/{id}")
	@GetMapping
    public ResponseEntity<CompanyResponseDTO> getCompanieById(@PathVariable Integer id) {
        Company company = companyService.getById(id);
        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(company);

        return new ResponseEntity<>(companyResponseDTO, HttpStatus.OK);
    }
	@GetMapping(value = "/{companyId}/equipment")
	public ResponseEntity<List<EquipmentDTO>> getCompanyEquipment(@PathVariable Integer companyId) {

		Company company = companyService.findOneWithEquipment(companyId);

		Set<Equipment> equipment = company.getEquipment();
		List<EquipmentDTO> equipmentDTOs = new ArrayList<>();

		for (Equipment e : equipment) {
			equipmentDTOs.add(new EquipmentDTO(e));
		}
		return new ResponseEntity<>(equipmentDTOs, HttpStatus.OK);
	}
}
