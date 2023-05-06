package danekerscode.socialmediaapi.service.interfaces;

import danekerscode.socialmediaapi.model.Message;
import danekerscode.socialmediaapi.payload.request.MessageRequest;

public interface MessageService extends ParentService<Message>{
    Message save(MessageRequest messageRequest);
}
