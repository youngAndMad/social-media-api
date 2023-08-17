package code.mailsenderservice.service;

import code.mailsenderservice.utils.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{

    private final JavaMailSender mailSender;

    @Value("${spring.mail.from}")
    private String from;


    @Override
    public void send(EmailRequest request) {
        var msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(request.email());
        msg.setSubject("Social media api");
        msg.setText(request.message());
        mailSender.send(msg);
    }

}
