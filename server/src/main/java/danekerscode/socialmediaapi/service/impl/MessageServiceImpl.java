package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.exception.EntityNotFoundException;
import danekerscode.socialmediaapi.exception.EntityPropertiesException;
import danekerscode.socialmediaapi.exception.UserNotFoundException;
import danekerscode.socialmediaapi.mapper.MessageMapper;
import danekerscode.socialmediaapi.model.Message;
import danekerscode.socialmediaapi.payload.request.MessageDTO;
import danekerscode.socialmediaapi.payload.request.UpdateMessageDTO;
import danekerscode.socialmediaapi.repository.ChatRepository;
import danekerscode.socialmediaapi.repository.MessageRepository;
import danekerscode.socialmediaapi.repository.UserRepository;
import danekerscode.socialmediaapi.service.ChatService;
import danekerscode.socialmediaapi.service.MessageService;
import danekerscode.socialmediaapi.service.UserService;
import danekerscode.socialmediaapi.utils.Converter;
import danekerscode.socialmediaapi.validate.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;
    private final ChatService chatService;
    private final MessageMapper messageMapper;


    @Override
    public Message save(MessageDTO request) {

        return messageRepository.save(
                messageMapper.toMessage(
                        request,
                        userService.getById(request.sender()),
                        chatService.getById(request.chatId())
                )
        );
    }

    @Override
    public void update(UpdateMessageDTO request, Integer id) {
        var msg = messageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Message.class));
        messageMapper.update(msg,request);
        messageRepository.save(msg);
    }

    @Override
    public void deleteById(Integer id) {
        messageRepository.deleteById(id);
    }

}
