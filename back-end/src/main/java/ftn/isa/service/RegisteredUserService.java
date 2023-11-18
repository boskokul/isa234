package ftn.isa.service;

import ftn.isa.domain.RegisteredUser;
import ftn.isa.domain.Student;
import ftn.isa.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisteredUserService {
    @Autowired
    RegisteredUserRepository registeredUserRepository;
    public List<RegisteredUser> findAll() {
        return registeredUserRepository.findAll();
    }
    public RegisteredUser save(RegisteredUser user) {
        return registeredUserRepository.save(user);
    }
    public RegisteredUser findByEmail(String email) {
        return registeredUserRepository.findOneByEmail(email);
    }
    public RegisteredUser findOne(int id){ return registeredUserRepository.findById(id).orElseGet(null); }
}
