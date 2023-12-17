package ftn.isa.service;

import java.util.List;

import ftn.isa.domain.SystemAdmin;
import ftn.isa.repository.SysAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class SysAdminService {
    @Autowired
    SysAdminRepository sARepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public List<SystemAdmin> findAll() {
        return sARepository.findAll();
    }
    public SystemAdmin save(SystemAdmin user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return sARepository.save(user);
    }


    public SystemAdmin update(SystemAdmin user) {
        return sARepository.save(user);
    }
    public SystemAdmin findOne(int id){ return sARepository.findById(id).orElseGet(null); }
}
