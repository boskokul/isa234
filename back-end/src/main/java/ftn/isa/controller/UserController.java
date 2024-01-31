package ftn.isa.controller;

import ftn.isa.domain.BaseUser;
import ftn.isa.domain.RegisteredUser;
import ftn.isa.domain.Role;
import ftn.isa.domain.SecureToken;
import ftn.isa.dto.UserCreateDTO;
import ftn.isa.dto.UserResponseDTO;
import ftn.isa.dto.UserUpdateDTO;
import ftn.isa.dto.UserVerificationDTO;
import ftn.isa.service.EmailService;
import ftn.isa.service.RegisteredUserService;
import ftn.isa.service.SecureTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/users")
public class UserController {
    @Autowired
    private RegisteredUserService userService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        List<RegisteredUser> users = userService.findAll();
        List<UserResponseDTO> userDTOs = new ArrayList<UserResponseDTO>();
        for(RegisteredUser u : users){
            userDTOs.add(new UserResponseDTO(u));
        }
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('REGISTERED_USER')")
    @PutMapping(consumes = "application/json")
    public ResponseEntity<UserResponseDTO> update(@RequestBody UserUpdateDTO userUpdateDTO){
        RegisteredUser user = userService.findOne(userUpdateDTO.getId());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());
        user.setCity(userUpdateDTO.getCity());
        user.setCountry(userUpdateDTO.getCountry());
        user.setPhoneNumber(userUpdateDTO.getPhoneNumber());
        user.setProfession(userUpdateDTO.getProfession());
        user.setCompanyInformation(userUpdateDTO.getCompanyInformation());

        user = userService.update(user);
        return new ResponseEntity<>(new UserResponseDTO(user), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Integer id){
        RegisteredUser user = userService.findOne(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new UserResponseDTO(user), HttpStatus.OK);
    }

    @GetMapping(value = "/isUserValidForBuying/{id}")
    public ResponseEntity<Boolean> isUserValidForBuying(@PathVariable Integer id){
        RegisteredUser user = userService.findOne(id);
        if(user != null){
            if(user.getPenalPoints() < 3){
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
