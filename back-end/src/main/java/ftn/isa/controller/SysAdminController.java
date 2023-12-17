package ftn.isa.controller;

import ftn.isa.domain.*;
import ftn.isa.dto.*;
import ftn.isa.service.RoleService;
import ftn.isa.service.SysAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import ftn.isa.domain.BaseUser;
import ftn.isa.domain.Role;
import ftn.isa.dto.UserVerificationDTO;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/sys/admins")
public class SysAdminController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SysAdminService sysAdminService;
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<SysAdminResponseDTO> getById(@PathVariable Integer id){
        SystemAdmin user = sysAdminService.findOne(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new SysAdminResponseDTO(user), HttpStatus.OK);
    }

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

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @PutMapping(value="/pass",consumes = "application/json")
    public ResponseEntity<SysAdminResponseDTO> updatePassword(@RequestBody SysAdminResponseDTO userUpdateDTO){
        SystemAdmin user = sysAdminService.findOne(userUpdateDTO.getId());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        user.setVerified(true);
        user = sysAdminService.update(user);
        return new ResponseEntity<>(new SysAdminResponseDTO(user), HttpStatus.OK);
    }
    @GetMapping(value = "/isverified/{id}")
    public ResponseEntity<UserVerificationDTO> getVerificationById(@PathVariable Integer id){
        BaseUser user = sysAdminService.findOne(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new UserVerificationDTO(user), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @PutMapping(value = "/verify", consumes = "application/json")
    public ResponseEntity<UserVerificationDTO> update(@RequestBody UserVerificationDTO userUpdateDTO){
        BaseUser user = sysAdminService.findOne(userUpdateDTO.getId());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setVerified(true);
        return new ResponseEntity<>(new UserVerificationDTO(user), HttpStatus.OK);
    }
}
