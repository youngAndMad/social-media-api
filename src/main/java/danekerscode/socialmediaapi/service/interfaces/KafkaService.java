package danekerscode.socialmediaapi.service.interfaces;

public interface KafkaService {
    void sendEmailRequest(String email , String topic);
}
