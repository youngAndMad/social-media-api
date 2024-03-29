package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.exception.EntityNotFoundException;
import danekerscode.socialmediaapi.mapper.MessageMapper;
import danekerscode.socialmediaapi.model.Message;
import danekerscode.socialmediaapi.payload.request.MessageDTO;
import danekerscode.socialmediaapi.payload.request.UpdateMessageDTO;
import danekerscode.socialmediaapi.repository.MessageRepository;
import danekerscode.socialmediaapi.service.ChatService;
import danekerscode.socialmediaapi.service.MessageService;
import danekerscode.socialmediaapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
