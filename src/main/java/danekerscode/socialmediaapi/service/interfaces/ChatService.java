package danekerscode.socialmediaapi.service.interfaces;

import danekerscode.socialmediaapi.model.Chat;

public interface ChatService extends ParentService<Chat>{
    void leaveChat(Integer userId , Integer chatId);
}
