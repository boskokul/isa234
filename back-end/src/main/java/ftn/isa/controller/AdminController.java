package ftn.isa.controller;

import ftn.isa.domain.Company;
import ftn.isa.domain.Role;
import ftn.isa.domain.Student;
import ftn.isa.domain.User;
import ftn.isa.dto.AdminCreateDTO;
import ftn.isa.dto.CompanyResponseDTO;
import ftn.isa.dto.UserCreateDTO;
import ftn.isa.dto.UserResponseDTO;
import ftn.isa.service.CompanyService;
import ftn.isa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/admins")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @GetMapping(value = "/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        List<User> users = userService.findAll();
        List<UserResponseDTO> userDTOs = new ArrayList<UserResponseDTO>();
        for(User u : users){
            userDTOs.add(new UserResponseDTO(u));
        }
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/admin/company/{id}")
    public ResponseEntity<CompanyResponseDTO> getAdminCompany(@PathVariable Integer id){
        User admin = userService.findOne(id);
        if(admin==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CompanyResponseDTO companyResponseDTO = new CompanyResponseDTO(admin.getCompany());

        return new ResponseEntity<>(companyResponseDTO, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserResponseDTO> save(@RequestBody AdminCreateDTO userDTO){


        Company company = companyService.findOne(userDTO.getCompanyId());

        if (company == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        User user = new User();
        user.setCompany(companyService.findOne(userDTO.getCompanyId()));
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setPassword(userDTO.getPassword());
        user.setCompanyInformation(userDTO.getCompanyInformation());
        user.setProfession(userDTO.getProfession());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setRole(Role.CompanyAdmin);
        user = userService.save(user);
        UserResponseDTO userResponseDTO = new UserResponseDTO(user);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }
}