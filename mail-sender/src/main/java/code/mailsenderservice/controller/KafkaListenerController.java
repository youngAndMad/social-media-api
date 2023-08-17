package code.mailsenderservice.controller;

import code.mailsenderservice.service.MailService;
import code.mailsenderservice.utils.EmailRequest;
import code.mailsenderservice.utils.MailMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaListenerController{

    private final MailService mailService;

    @KafkaListener(topics = "greeting" , groupId = "myGroup")
    void greetingListener(EmailRequest request){
        mailService.greeting(request);
    }

    @KafkaListener(topics = "resetPassword" , groupId = "myGroup")
    void resetPassword(EmailRequest request){
        mailService.sendCodeToUpdatePassword(request);
    }

    @KafkaListener(topics = "activationCode" , groupId = "myGroup")
    void sendActivationCode(EmailRequest request){
        mailService.sendActivationCode(request);
    }

}
