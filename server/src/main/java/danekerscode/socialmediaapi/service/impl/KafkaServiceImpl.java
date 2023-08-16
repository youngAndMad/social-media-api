package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<String , String> kafkaTemplate;

    @Override
    public void sendEmailRequest(String email , String topic){
        kafkaTemplate.send(topic ,email);
    }

}
