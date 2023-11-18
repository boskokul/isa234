package ftn.isa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import ftn.isa.domain.*;
import ftn.isa.dto.CompanyAdminResponseDTO;
import ftn.isa.dto.CompanyCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import antlr.debug.Event;

import org.springframework.web.bind.annotation.*;
import ftn.isa.dto.CompanyResponseDTO;
import ftn.isa.dto.EquipmentDTO;
import ftn.isa.service.CompanyService;
import ftn.isa.dto.UserResponseDTO;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/companies")
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	@GetMapping(value = "/all")
    public ResponseEntity<List<CompanyResponseDTO>> getCompanies() {
        List<Company> companies = companyService.findAll();

        List<CompanyResponseDTO> companyResponseDTOs = new ArrayList<>();
        for (Company c : companies) {
        	companyResponseDTOs.add(new CompanyResponseDTO(c));
        }

        return new ResponseEntity<>(companyResponseDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/all/admins/{id}")
    public ResponseEntity<List<CompanyAdminResponseDTO>> getCompanyAdmins(@PathVariable Integer id) {
        Company company = companyService.findOne(id);
        if(company==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<CompanyAdminResponseDTO> companyAdminResponseDTOs = company.getCompanyAdmins()
                .stream()
                .map(CompanyAdminResponseDTO::new)
                .toList();

        return new ResponseEntity<>(companyAdminResponseDTOs, HttpStatus.OK);
    }

	@GetMapping(value = "/last/created/id")
    public ResponseEntity<Integer> getLastCreatedId() {
        Integer id = companyService.getLastCompanyId();
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyResponseDTO> getCompanieById(@PathVariable Integer id) {
        Company company = companyService.getById(id);
        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(company);

        return new ResponseEntity<>(companyResponseDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CompanyResponseDTO> save(@RequestBody CompanyCreateDTO companyDTO){
        Company company = new Company();
        company.setCountry(companyDTO.getCountry());
        company.setCity(companyDTO.getCity());
        company.setName(companyDTO.getName());
        company.setDescription(companyDTO.getDescription());
        company.setAverageGrade(companyDTO.getAverageGrade());

        company = companyService.save(company);


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

    @GetMapping(value = "/search")
    public ResponseEntity<List<CompanyResponseDTO>> searchNameCountryCity(@RequestParam(required = false) String name, @RequestParam(required = false) String country, @RequestParam(required = false) String city){
        if(name == null){ name = ""; }
        if(country == null){ country = ""; }
        if(city == null){ city = ""; }

        List<Company> companies = companyService.searchNameCountryCity(name, country, city);
        if(companies == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<CompanyResponseDTO> responseDTOS = new ArrayList<>();
        for(Company c: companies){
            responseDTOS.add(new CompanyResponseDTO(c));
        }
        return new ResponseEntity<>(responseDTOS, HttpStatus.OK);
    }
    
    @PutMapping(consumes = "application/json")
    public ResponseEntity<CompanyResponseDTO> update(@RequestBody CompanyResponseDTO companyUpdateDTO){
        Company c = companyService.findOne(companyUpdateDTO.getId());

        if (c == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        c.setName(companyUpdateDTO.getName());
        c.setDescription(companyUpdateDTO.getDescription());
        c.setCity(companyUpdateDTO.getCity());
        c.setCountry(companyUpdateDTO.getCountry());

        c = companyService.save(c);
        return new ResponseEntity<>(new CompanyResponseDTO(c), HttpStatus.OK);
    }

}
