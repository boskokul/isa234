package ftn.isa.controller;

import ftn.isa.domain.BaseUser;
import ftn.isa.domain.RegisteredUser;
import ftn.isa.domain.Role;
import ftn.isa.domain.SecureToken;
import ftn.isa.dto.UserCreateDTO;
import ftn.isa.dto.UserResponseDTO;
import ftn.isa.dto.UserUpdateDTO;
import ftn.isa.service.EmailService;
import ftn.isa.service.RegisteredUserService;
import ftn.isa.service.SecureTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/users")
public class UserController {
    @Autowired
    private RegisteredUserService userService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private SecureTokenService tokenService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @GetMapping(value = "/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        List<RegisteredUser> users = userService.findAll();
        List<UserResponseDTO> userDTOs = new ArrayList<UserResponseDTO>();
        for(RegisteredUser u : users){
            userDTOs.add(new UserResponseDTO(u));
        }
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }
    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserResponseDTO> save(@RequestBody UserCreateDTO userDTO){
        RegisteredUser userWithMail = userService.findByEmail(userDTO.getEmail());
        if(userWithMail != null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        RegisteredUser user = new RegisteredUser();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setPassword(userDTO.getPassword());
        user.setCompanyInformation(userDTO.getCompanyInformation());
        user.setProfession(userDTO.getProfession());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setRole(Role.RegisteredUser);
        user.setVerified(false);
        user = userService.save(user);
        sendRegistrationConfirmationEmail(user);
        UserResponseDTO userResponseDTO = new UserResponseDTO(user);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

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

        user = userService.save(user);
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

    @GetMapping(value = "/registrationConfirm")
    public String confirmRegistration(@RequestParam("token") String token){
        SecureToken secureToken = tokenService.findByToken(token);
        if(secureToken == null){
            return "redirect: http://localhost:4200/invalidtoken";
        }
        RegisteredUser user = secureToken.getUser();
        user.setVerified(true);
        userService.save(user);
        tokenService.removeToken(secureToken);
        return "redirect: http://localhost:4200/verification";
    }

    private void sendRegistrationConfirmationEmail(RegisteredUser user) {
        SecureToken secureToken= tokenService.createSecureToken();
        secureToken.setUser(user);
        tokenService.saveSecureToken(secureToken);
        try {
            emailService.sendVerificationMail(user, secureToken.getToken());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
