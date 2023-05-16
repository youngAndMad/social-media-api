package code.mailsenderservice.service;

import code.mailsenderservice.utils.EmailRequest;
import code.mailsenderservice.utils.MailMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

import static java.util.UUID.randomUUID;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{

    private final JdbcTemplate jdbcTemplate;
    private final JavaMailSender mailSender;

    @Override
    public void send(MailMessageRequest request) {
        mailSender.send(createMessage(request.to() , request.subject(), request.message()));
    }

    @Override
    public void greeting(EmailRequest request) {
        System.out.println("from service impl: "+request.email());
        mailSender.send(createMessage(request.email(), "Social messenger", "Wanna be software engineer"));
    }

    @Override
    public void sendCodeToUpdatePassword(EmailRequest request) {
        String code = randomUUID().toString().substring(0, 6);
        setCode(request.email() , code);
        mailSender.send(createMessage(request.email(), "update password", code));
    }

    @Override
    public void sendActivationCode(EmailRequest request) {
        Random random = new Random();
        String code =String.valueOf(random.ints(100_000 , 999_999).findFirst().getAsInt());
        setCode(request.email() , code);
        mailSender.send(createMessage(request.email() , "activation code" , code));
    }

    public SimpleMailMessage createMessage(String to , String subj , String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("social-media@gmail.com");
        mailMessage.setTo(to);
        mailMessage.setSubject(subj);
        mailMessage.setText(message);
        return mailMessage;
    }

    private void setCode(String email , String code){
        jdbcTemplate.update("update users set code = ? where email = ?", code, email);
    }

}
