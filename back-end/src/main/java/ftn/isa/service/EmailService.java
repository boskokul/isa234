package ftn.isa.service;

import ftn.isa.domain.RegisteredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    public void sendVerificationMail(RegisteredUser user, String token) throws MailException, InterruptedException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Account verification");
        mail.setText("Hi, " + user.getFirstName() + ",\n\nClick on link below to verificate your account on" +
                " medical equipment management system. \n http://localhost:8084/api/registration/registrationConfirm?token="+token);
        javaMailSender.send(mail);
    }
}
