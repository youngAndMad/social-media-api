package danekerscode.socialmediaapi.service;

import danekerscode.socialmediaapi.utils.KafkaMailMessage;

public interface KafkaService {
    void sendEmailRequest(KafkaMailMessage mailMessage);
}
