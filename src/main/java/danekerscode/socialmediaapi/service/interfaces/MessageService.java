package danekerscode.socialmediaapi.service.interfaces;

import danekerscode.socialmediaapi.model.Message;
import danekerscode.socialmediaapi.payload.request.MessageRequest;
import danekerscode.socialmediaapi.payload.request.UpdateMessageRequest;

public interface MessageService extends ParentService<Message>{
    Message save(MessageRequest messageRequest);

    void update(UpdateMessageRequest request, Integer id);
}
