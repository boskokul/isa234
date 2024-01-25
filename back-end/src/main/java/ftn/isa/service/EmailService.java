package ftn.isa.service;

import com.google.zxing.WriterException;
import ftn.isa.domain.RegisteredUser;
import ftn.isa.util.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;


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
    public void sendReservationConfirmationQR(String data, String userMail) throws MailException, MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true, "utf-8");
        String image = makeQr(data);
        helper.setText("<html><body><img src='cid:image' /></body></html>", true);
        helper.setTo(userMail);
        helper.setSubject("Reservation QR");
        helper.setFrom(env.getProperty("spring.mail.username"));
        helper.addInline("image", new ByteArrayResource(Base64.getDecoder().decode(image)), "image/png");
        javaMailSender.send(mimeMessage);
    }
    private String makeQr(String data){
        byte[] image = new byte[0];
        try {
            image = QRCodeGenerator.getQRCodeImage(data,250,250);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        String qrcode = Base64.getEncoder().encodeToString(image);
        return qrcode;
    }

    public void sendPickupConfirmationMail(RegisteredUser user, String token) throws MailException, InterruptedException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Pickup confirmation");
        mail.setText(token+"\n");
        javaMailSender.send(mail);
    }

}
