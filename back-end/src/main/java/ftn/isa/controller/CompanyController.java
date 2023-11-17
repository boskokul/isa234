package ftn.isa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ftn.isa.dto.CompanyCreateDTO;
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
import org.springframework.web.bind.annotation.*;
import ftn.isa.domain.Company;
import ftn.isa.dto.CompanyResponseDTO;
import ftn.isa.service.CompanyService;
import ftn.isa.domain.Role;
import ftn.isa.domain.Student;
import ftn.isa.domain.User;
import ftn.isa.dto.UserCreateDTO;
import ftn.isa.dto.UserResponseDTO;
import ftn.isa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<List<UserResponseDTO>> getCompanyAdmins(@PathVariable Integer id) {
        Company company = companyService.findOne(id);
        if(company==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<UserResponseDTO> userResponseDTOs = company.getAdmins()
                .stream()
                .map(UserResponseDTO::new)
                .toList();

        return new ResponseEntity<>(userResponseDTOs, HttpStatus.OK);
    }

	@PostMapping(value = "/{id}")
	@GetMapping
    public ResponseEntity<CompanyResponseDTO> getCompanieById(@PathVariable Integer id) {
        Company company = companyService.getById(id);
        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(company);

        return new ResponseEntity<>(companyResponseDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CompanyResponseDTO> save(@RequestBody CompanyCreateDTO companyDTO){
        Company company = new Company();
        company.setAdress(companyDTO.getAdress());
        company.setName(companyDTO.getName());
        company.setDescription(companyDTO.getDescription());
        company.setAverageGrade(companyDTO.getAverageGrade());

        company = companyService.save(company);


        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(company);
        return new ResponseEntity<>(companyResponseDTO, HttpStatus.OK);
    }






}
