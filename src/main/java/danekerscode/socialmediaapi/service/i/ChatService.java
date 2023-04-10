package danekerscode.socialmediaapi.service.i;

import danekerscode.socialmediaapi.model.Chat;

import java.util.List;

public interface ChatService extends ParentService<Chat>{
    void leaveChat(Integer userId , Integer chatId);
}
