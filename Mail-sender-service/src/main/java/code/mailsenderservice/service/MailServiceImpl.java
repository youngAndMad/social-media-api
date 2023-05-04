package code.mailsenderservice.service;

import code.mailsenderservice.utils.EmailRequest;
import code.mailsenderservice.utils.MailMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static java.util.UUID.randomUUID;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{

    private final JdbcTemplate jdbcTemplate;
    private final JavaMailSender mailSender;

    @Override
    public void send(MailMessageRequest request) {
        mailSender.send(createMessage(new MailMessageRequest(request.to() , request.subject(), request.message())));
    }

    @Override
    public void greeting(EmailRequest request) {
        System.out.println("from service impl: "+request.email());
        mailSender.send(createMessage(new MailMessageRequest(request.email(), "Social messenger", "Wanna be software engineer")));
    }

    @Override
    public void sendCodeToUpdatePassword(EmailRequest request) {
        String code = randomUUID().toString().substring(0, 6);
        jdbcTemplate.update("update users set code = ? where email = ?", code, request.email());
        mailSender.send(createMessage(new MailMessageRequest(request.email(), "update password", code)));
    }

    public SimpleMailMessage createMessage(MailMessageRequest request) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("social-media@gmail.com");
        mailMessage.setTo(request.to());
        mailMessage.setSubject(request.subject());
        mailMessage.setText(request.message());
        return mailMessage;
    }

}
