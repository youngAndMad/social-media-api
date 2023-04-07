package danekerscode.socialmediaapi.service.i;

import danekerscode.socialmediaapi.payload.request.MailMessageRequest;
import org.springframework.mail.SimpleMailMessage;

public interface MailService {
    void sendGreeting(String email);
    void sendCodeToUpdatePassword(String email);
    SimpleMailMessage createMessage(MailMessageRequest request);
}
