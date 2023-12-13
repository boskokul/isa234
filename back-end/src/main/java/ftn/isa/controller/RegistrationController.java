package ftn.isa.controller;

import ftn.isa.domain.BaseUser;
import ftn.isa.domain.RegisteredUser;
import ftn.isa.domain.Role;
import ftn.isa.domain.SecureToken;
import ftn.isa.dto.JwtAuthenticationRequest;
import ftn.isa.dto.UserCreateDTO;
import ftn.isa.dto.UserResponseDTO;
import ftn.isa.dto.UserTokenState;
import ftn.isa.service.EmailService;
import ftn.isa.service.RegisteredUserService;
import ftn.isa.service.RoleService;
import ftn.isa.service.SecureTokenService;
import ftn.isa.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/registration")
public class RegistrationController {
    @Autowired
    private RegisteredUserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private SecureTokenService tokenService;
    @Autowired
    private RoleService roleService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserResponseDTO> save(@RequestBody UserCreateDTO userDTO){
        RegisteredUser userWithMail = userService.findByEmail(userDTO.getEmail());
        if(userWithMail != null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        RegisteredUser user = new RegisteredUser();
        user.setUsername(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setPassword(userDTO.getPassword());
        user.setCompanyInformation(userDTO.getCompanyInformation());
        user.setProfession(userDTO.getProfession());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        List<Role> roles = roleService.findByName("ROLE_REGISTERED_USER");
        user.setRoles(roles);
        user.setVerified(true);
        user = userService.save(user);
        //sendRegistrationConfirmationEmail(user);
        UserResponseDTO userResponseDTO = new UserResponseDTO(user);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {
        // Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
        // AuthenticationException
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(), authenticationRequest.getPassword()));

        // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
        // kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Kreiraj token za tog korisnika
        BaseUser user = (BaseUser) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername(), user.getRoles().get(0).getName());
        int expiresIn = tokenUtils.getExpiredIn();

        // Vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
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
