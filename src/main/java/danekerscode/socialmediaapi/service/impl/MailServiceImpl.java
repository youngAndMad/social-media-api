package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.payload.request.MailMessageRequest;
import danekerscode.socialmediaapi.service.interfaces.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    private final JdbcTemplate jdbcTemplate;

    public void sendGreeting(String email){
        mailSender.send(createMessage(new MailMessageRequest(email, "hello", "маған дискретка унамайды")));
    }

    public void sendCodeToUpdatePassword(String email){
        String code = UUID.randomUUID().toString().substring(0,5);
        jdbcTemplate.update("update users set code = ? where email = ?" , code , email);
        mailSender.send(createMessage(new MailMessageRequest(email , "update password" , code)));
    }

    public SimpleMailMessage createMessage(MailMessageRequest request){

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("social-media@gmail.com");
        mailMessage.setTo(request.to());
        mailMessage.setSubject(request.subject());
        mailMessage.setText(request.message());
        return mailMessage;

    }
}
