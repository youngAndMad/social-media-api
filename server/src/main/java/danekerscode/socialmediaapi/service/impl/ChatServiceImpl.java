package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.exception.EntityNotFoundException;
import danekerscode.socialmediaapi.exception.EntityPropertiesException;
import danekerscode.socialmediaapi.exception.UserNotFoundException;
import danekerscode.socialmediaapi.mapper.ChatMapper;
import danekerscode.socialmediaapi.model.Chat;
import danekerscode.socialmediaapi.model.User;
import danekerscode.socialmediaapi.payload.request.ChatDTO;
import danekerscode.socialmediaapi.repository.ChatRepository;
import danekerscode.socialmediaapi.repository.UserRepository;
import danekerscode.socialmediaapi.service.ChatService;
import danekerscode.socialmediaapi.service.UserService;
import danekerscode.socialmediaapi.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;
    private final UserService userService;

    @Override
    public Chat createChat(ChatDTO chatDTO) {

        var firstUser = userService.getById(chatDTO.firstUserId());
        var secondUser = userService.getById(chatDTO.secondUserId());

        if (firstUser.getChats().stream()
                .anyMatch(chat -> secondUser.getChats().contains(chat))) {
            throw new EntityPropertiesException("these users already have a chat");
        }

        Set<User> users = new HashSet<>() {
            {
                add(firstUser);
                add(secondUser);
            }
        };

        Chat chat = chatMapper.toChat(chatDTO, users);
        return chatRepository.save(chat);
    }

    @Override
    public void deleteById(Integer id) {
        chatRepository.deleteById(id);
    }

    @Override
    public Chat getById(Integer id) {
        return chatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Chat.class));
    }


    @Override
    public void leaveChat(Integer userId, Integer chatId) {
        var user = userService.getById(userId);
        var chat = getById(chatId);

        chat.getUsers().remove(user);

        chatRepository.save(chat);
    }

    @Override
    public void joinChat(Integer userId, Integer chatId) {
        var user = userService.getById(userId);
        var chat = getById(chatId);

        chat.getUsers().add(user);

        chatRepository.save(chat);
    }

}
