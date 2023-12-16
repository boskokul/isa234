package ftn.isa.controller;

import ftn.isa.domain.Company;
import ftn.isa.domain.CompanyAdmin;
import ftn.isa.domain.RegisteredUser;
import ftn.isa.domain.Role;
import ftn.isa.dto.AdminCreateDTO;
import ftn.isa.dto.CompanyAdminResponseDTO;
import ftn.isa.dto.CompanyResponseDTO;
import ftn.isa.dto.UserResponseDTO;
import ftn.isa.dto.UserUpdateDTO;
import ftn.isa.service.CompanyAdminService;
import ftn.isa.service.CompanyService;
import ftn.isa.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/admins")
public class AdminController {
	@Autowired
    private CompanyAdminService companyAadminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyAdminResponseDTO> getById(@PathVariable Integer id){
    	CompanyAdmin user = companyAadminService.findOne(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new CompanyAdminResponseDTO(user), HttpStatus.OK);
    }
    
    @GetMapping(value = "/admin/company/{id}")
    public ResponseEntity<CompanyResponseDTO> getAdminCompany(@PathVariable Integer id){
        CompanyAdmin admin = companyAadminService.findOne(id);
        if(admin==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(admin.getCompany());

        return new ResponseEntity<>(companyResponseDTO, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<CompanyAdminResponseDTO> save(@RequestBody AdminCreateDTO userDTO){

        Company company = companyService.findOne(userDTO.getCompanyId());

        if (company == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CompanyAdmin user = new CompanyAdmin();
        user.setCompany(companyService.findOne(userDTO.getCompanyId()));
        user.setUsername(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        List<Role> roles = roleService.findByName("ROLE_COMPANY_ADMIN");
        user.setRoles(roles);
        user.setVerified(false);
        user = companyAadminService.save(user);
        CompanyAdminResponseDTO userResponseDTO = new CompanyAdminResponseDTO(user);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('COMPANY_ADMIN')")
    @PutMapping(consumes = "application/json")
    public ResponseEntity<CompanyAdminResponseDTO> update(@RequestBody CompanyAdminResponseDTO userUpdateDTO){
        CompanyAdmin user = companyAadminService.findOne(userUpdateDTO.getId());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());
        user.setCity(userUpdateDTO.getCity());
        user.setCountry(userUpdateDTO.getCountry());
        user.setPhoneNumber(userUpdateDTO.getPhoneNumber());
        user.setPassword(userUpdateDTO.getPassword());
        user = companyAadminService.update(user);
        return new ResponseEntity<>(new CompanyAdminResponseDTO(user), HttpStatus.OK);
    }
}