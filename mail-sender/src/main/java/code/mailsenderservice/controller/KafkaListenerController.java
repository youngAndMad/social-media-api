package code.mailsenderservice.controller;

import code.mailsenderservice.service.MailService;
import code.mailsenderservice.utils.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaListenerController{

    private final MailService mailService;

    @KafkaListener(topics = "mail_topic" , groupId = "myGroup")
    void greetingListener(
            EmailRequest emailRequest
    ){
        System.out.println(emailRequest);
//        mailService.greeting(request)/;
    }


}
