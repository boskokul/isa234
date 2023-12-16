package ftn.isa.controller;

import ftn.isa.domain.*;
import ftn.isa.dto.*;
import ftn.isa.service.CompanyAdminService;
import ftn.isa.service.CompanyService;
import ftn.isa.service.RoleService;
import ftn.isa.service.SysAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/sys/admins")
public class SysAdminController {
    @Autowired
    private SysAdminService sysAdminService;
    @Autowired
    private RoleService roleService;


    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @PostMapping(consumes = "application/json")
    public ResponseEntity<SysAdminResponseDTO> save(@RequestBody SysAdminCreateDTO userDTO){

        SystemAdmin user = new SystemAdmin();
        user.setUsername(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        List<Role> roles = roleService.findByName("ROLE_SYSTEM_ADMIN");
        user.setRoles(roles);
        user.setVerified(false);
        user = sysAdminService.save(user);
        SysAdminResponseDTO userResponseDTO = new SysAdminResponseDTO(user);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @PutMapping(consumes = "application/json")
    public ResponseEntity<SysAdminResponseDTO> update(@RequestBody SysAdminResponseDTO userUpdateDTO){
        SystemAdmin user = sysAdminService.findOne(userUpdateDTO.getId());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());
        user.setCity(userUpdateDTO.getCity());
        user.setCountry(userUpdateDTO.getCountry());
        user.setPhoneNumber(userUpdateDTO.getPhoneNumber());
        user.setPassword(userUpdateDTO.getPassword());
        user = sysAdminService.update(user);
        return new ResponseEntity<>(new SysAdminResponseDTO(user), HttpStatus.OK);
    }
}
