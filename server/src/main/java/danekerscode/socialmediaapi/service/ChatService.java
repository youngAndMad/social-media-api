package danekerscode.socialmediaapi.service;

import danekerscode.socialmediaapi.model.Chat;
import danekerscode.socialmediaapi.payload.request.ChatRequest;


public interface ChatService extends ParentService<Chat> {
    void leaveChat(Integer userId , Integer chatId);

    void joinChat(Integer userId, Integer chatId);

    Chat createChat(ChatRequest chatRequest);
}
