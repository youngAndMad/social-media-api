package danekerscode.socialmediaapi.validate;

import danekerscode.socialmediaapi.constants.ChannelContent;
import danekerscode.socialmediaapi.exception.EntityPropertiesException;
import danekerscode.socialmediaapi.exception.RegistratoinException;
import danekerscode.socialmediaapi.exception.UserNotFoundException;
import danekerscode.socialmediaapi.repository.ChannelRepository;
import danekerscode.socialmediaapi.repository.ChatRepository;
import danekerscode.socialmediaapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import danekerscode.socialmediaapi.payload.request.*;

import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class CustomValidator {

    private final UserRepository repository;
    private final ChannelRepository channelRepository;
    private final ChatRepository chatRepository;


    public boolean validateEmail(String email) {
        var regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }


    public void validatePost(PostDTO postDTO) {
        if (postDTO.body().isEmpty() || postDTO.title().isEmpty()) {
            throw new EntityPropertiesException("specify all properties");
        }
        if (channelRepository.findById(postDTO.ownerChannelId()).isEmpty()) {
            throw new EntityPropertiesException("invalid id for owner channel id");
        }
    }

    public void validateMessage(MessageDTO request) {
        if (chatRepository.findById(request.chatId()).isEmpty())
            throw new EntityPropertiesException("invalid chat id");

        if (repository.findById(request.sender()).isEmpty())
            throw new UserNotFoundException();

        if (request.text().trim().isBlank())
            throw new EntityPropertiesException("invalid message");
    }

    private boolean validateGender(String gender){
        return gender.compareToIgnoreCase("MALE") != 0 && gender.compareToIgnoreCase("FEMALE") != 0;
    }
}

