package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.constants.ChatType;
import danekerscode.socialmediaapi.exception.EntityPropertiesException;
import danekerscode.socialmediaapi.exception.UserNotFoundException;
import danekerscode.socialmediaapi.model.Chat;
import danekerscode.socialmediaapi.model.User;
import danekerscode.socialmediaapi.payload.request.ChatRequest;
import danekerscode.socialmediaapi.repository.ChatRepository;
import danekerscode.socialmediaapi.repository.UserRepository;
import danekerscode.socialmediaapi.service.interfaces.ChatService;
import danekerscode.socialmediaapi.validate.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static danekerscode.socialmediaapi.utils.Converter.toChat;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    @Override
    public Chat createChat(ChatRequest chatRequest) {

        var firstUser = userRepository.findById(chatRequest.firstUserId()).orElseThrow(UserNotFoundException::new);
        var secondUser = userRepository.findById(chatRequest.secondUserId()).orElseThrow(UserNotFoundException::new);

        if (firstUser.getChats().stream()
                .anyMatch(chat -> secondUser.getChats().contains(chat))) {
            throw new EntityPropertiesException("these users already have a chat");
        }

        List<User> users = new ArrayList<>() {
            {
                add(firstUser);
                add(secondUser);
            }
        };

        Chat chat = toChat(chatRequest, users);
        return chatRepository.save(chat);
    }

    @Override
    public void deleteByID(Integer id) {
        chatRepository.deleteById(id);
    }

    @Override
    public Optional<Chat> getById(Integer id) {
        return chatRepository.findById(id);
    }

    @Override
    public List<Chat> getAll() {
        return chatRepository.findAll();
    }

    @Override
    public void leaveChat(Integer userId, Integer chatId) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        var chat = chatRepository.findById(chatId).orElseThrow(() -> new EntityPropertiesException("invalid chat id"));
        user.getChats().remove(chat);
        userRepository.save(user);
    }

    @Override
    public void joinChat(Integer userId, Integer chatId) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        var chat = chatRepository.findById(chatId).orElseThrow(() -> new EntityPropertiesException("invalid chat id"));
        user.getChats().add(chat);
        userRepository.save(user);
    }

}
