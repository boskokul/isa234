package ftn.isa.service;

import ftn.isa.domain.RegisteredUser;
import ftn.isa.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class PenaltyChecker {

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Transactional
    @Scheduled(cron = "0 0 0 1 */1 ?")
    public void deleteAllPenalties(){
        for (RegisteredUser user:
             registeredUserRepository.findAllByPenalPointsGreaterThan(0)) {
                user.setPenalPoints(0);
                registeredUserRepository.save(user);
        }
        System.out.println("All penalty points have been deleted");
    }
}
