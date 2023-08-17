package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.service.KafkaService;
import danekerscode.socialmediaapi.utils.KafkaMailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    @Value("${spring.kafka.topics.mail}")
    private String mailTopic;

    private final KafkaTemplate<String , KafkaMailMessage> kafkaTemplate;

    @Override
    public void sendEmailRequest(KafkaMailMessage kafkaMailMessage){
        kafkaTemplate.send(mailTopic, kafkaMailMessage);
    }

}
