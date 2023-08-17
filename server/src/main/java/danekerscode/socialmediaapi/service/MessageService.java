package danekerscode.socialmediaapi.service;

import danekerscode.socialmediaapi.model.Message;
import danekerscode.socialmediaapi.payload.request.MessageDTO;
import danekerscode.socialmediaapi.payload.request.UpdateMessageDTO;

public interface MessageService {
    Message save(MessageDTO messageDTO);

    void update(UpdateMessageDTO request, Integer id);

    void deleteById(Integer id);
}
