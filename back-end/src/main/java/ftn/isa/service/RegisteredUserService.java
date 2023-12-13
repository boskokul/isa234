package ftn.isa.service;

import ftn.isa.domain.RegisteredUser;
import ftn.isa.domain.Student;
import ftn.isa.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisteredUserService {
    @Autowired
    RegisteredUserRepository registeredUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<RegisteredUser> findAll() {
        return registeredUserRepository.findAll();
    }
    public RegisteredUser save(RegisteredUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return registeredUserRepository.save(user);
    }
    public RegisteredUser findByEmail(String email) {
        return registeredUserRepository.findByUsername(email);
    }
    public RegisteredUser findOne(int id){ return registeredUserRepository.findById(id).orElseGet(null); }
}
