package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.exception.EntityPropertiesException;
import danekerscode.socialmediaapi.exception.UserNotFoundException;
import danekerscode.socialmediaapi.model.Message;
import danekerscode.socialmediaapi.payload.request.MessageRequest;
import danekerscode.socialmediaapi.payload.request.Request;
import danekerscode.socialmediaapi.repository.ChatRepository;
import danekerscode.socialmediaapi.repository.MessageRepository;
import danekerscode.socialmediaapi.repository.UserRepository;
import danekerscode.socialmediaapi.service.interfaces.MessageService;
import danekerscode.socialmediaapi.validate.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static danekerscode.socialmediaapi.utils.Converter.toMessage;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final CustomValidator customValidator;

    @Override
    public Message save(MessageRequest request) {
        customValidator.validateMessage(request);
        return messageRepository.save(
                toMessage(
                        request,
                        userRepository.findById(request.sender()).orElseThrow(UserNotFoundException::new),
                        chatRepository.findById(request.chatId()).orElseThrow(EntityNotFoundException::new))
        );
    }

    @Override
    public void deleteByID(Integer id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Optional<Message> getById(Integer id) {
        return messageRepository.findById(id);
    }

    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public void update(Request request, Integer id) {
        var msg = messageRepository.findById(id).orElseThrow(() -> new EntityPropertiesException("invalid message id"));
        msg.setText(((MessageRequest)request).text());
        msg.setId(id);
        messageRepository.save(msg);
    }
}
