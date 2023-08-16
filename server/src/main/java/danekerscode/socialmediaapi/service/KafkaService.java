package danekerscode.socialmediaapi.service;

public interface KafkaService {
    void sendEmailRequest(String email , String topic);
}
