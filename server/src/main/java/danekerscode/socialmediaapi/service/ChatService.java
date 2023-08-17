package danekerscode.socialmediaapi.service;

import danekerscode.socialmediaapi.model.Chat;
import danekerscode.socialmediaapi.payload.request.ChatDTO;


public interface ChatService {
    void leaveChat(Integer userId , Integer chatId);

    void joinChat(Integer userId, Integer chatId);

    Chat createChat(ChatDTO chatDTO);

    Chat getById(Integer id);

    void deleteById(Integer id);
}
