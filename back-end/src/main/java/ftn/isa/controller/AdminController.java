package ftn.isa.controller;

import ftn.isa.domain.Company;
import ftn.isa.domain.CompanyAdmin;
import ftn.isa.domain.Role;
import ftn.isa.dto.AdminCreateDTO;
import ftn.isa.dto.CompanyAdminResponseDTO;
import ftn.isa.dto.CompanyResponseDTO;
import ftn.isa.service.CompanyAdminService;
import ftn.isa.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/admins")
public class AdminController {
	@Autowired
    private CompanyAdminService companyAadminService;
    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/admin/company/{id}")
    public ResponseEntity<CompanyResponseDTO> getAdminCompany(@PathVariable Integer id){
        CompanyAdmin admin = companyAadminService.findOne(id);
        if(admin==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(admin.getCompany());

        return new ResponseEntity<>(companyResponseDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CompanyAdminResponseDTO> save(@RequestBody AdminCreateDTO userDTO){


        Company company = companyService.findOne(userDTO.getCompanyId());

        if (company == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        CompanyAdmin user = new CompanyAdmin();
        user.setCompany(companyService.findOne(userDTO.getCompanyId()));
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setRole(Role.CompanyAdmin);
        user = companyAadminService.save(user);
        CompanyAdminResponseDTO userResponseDTO = new CompanyAdminResponseDTO(user);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }
}