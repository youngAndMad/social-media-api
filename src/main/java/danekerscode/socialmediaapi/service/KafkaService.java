package danekerscode.socialmediaapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String , String> kafkaTemplate;

    public void sendEmailRequest(String email , String topic){
        kafkaTemplate.send(topic ,email);
    }


}
