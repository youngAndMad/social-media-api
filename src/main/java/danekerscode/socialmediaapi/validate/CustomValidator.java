package danekerscode.socialmediaapi.validate;

import danekerscode.socialmediaapi.constants.ChannelContent;
import danekerscode.socialmediaapi.exception.AuthenticationException;
import danekerscode.socialmediaapi.exception.EntityPropertiesException;
import danekerscode.socialmediaapi.exception.RegistratoinException;
import danekerscode.socialmediaapi.exception.UserNotFoundException;
import danekerscode.socialmediaapi.payload.request.*;
import danekerscode.socialmediaapi.repository.ChannelRepository;
import danekerscode.socialmediaapi.repository.ChatRepository;
import danekerscode.socialmediaapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Pattern;

import static danekerscode.socialmediaapi.constants.ChatType.GROUP_CHAT;
import static danekerscode.socialmediaapi.constants.ChatType.PRIVATE_CHAT;

@Component
@RequiredArgsConstructor
public class CustomValidator {

    private final UserRepository repository;
    private final ChannelRepository channelRepository;
    private final ChatRepository chatRepository;

    public void validateUserRequest(UserRequest request) {
        StringBuilder sb = new StringBuilder();
        if (repository.findUserByEmail(request.email()).isPresent())
            sb.append("this email ").append(request.email()).append(" registered yet!");
        if (!this.validateEmail(request.email()))
            sb.append("please specify valid email");
        if (request.gender().compareToIgnoreCase("MALE") != 0 && request.gender().compareToIgnoreCase("FEMALE") != 0)
            sb.append("please specify valid gender!");
        if (request.age() < 16)
            sb.append("for registration age should be greater than 16!");
        if (request.lastName().length() < 2)
            sb.append("length of  lastname should be longest than 2!");
        if (request.firstName().length() < 2)
            sb.append("length of firstname should be longest than 2!");
        if (!sb.isEmpty())
            throw new RegistratoinException(sb.toString());
    }

    public void validateUpdateUserRequest(UserUpdateRequest request) {
        StringBuilder sb = new StringBuilder();
        if (request.gender().compareToIgnoreCase("MALE") != 0 && request.gender().compareToIgnoreCase("FEMALE") != 0)
            sb.append("please specify valid gender!");
        if (request.age() < 16)
            sb.append("for registration age should be greater than 16!");
        if (request.lastName().length() < 2)
            sb.append("length of  lastname should be longest than 2!");
        if (request.firstName().length() < 2)
            sb.append("length of firstname should be longest than 2!");
        if (!sb.isEmpty())
            throw new RegistratoinException(sb.toString());

    }

    public boolean validateEmail(String email) {
        System.out.println(email);
        var regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }

    public void validateChannel(ChannelRequest request) {
        var user = repository.findById(request.owner());
        if (user.isEmpty())
            throw new UserNotFoundException();

        if (Arrays.stream(ChannelContent.values()).noneMatch(content ->
                content.name().equals(request.content().toUpperCase(Locale.ROOT))))
            throw new EntityPropertiesException("invalid content type of channel");

    }

    public void validatePost(PostRequest postRequest) {
        if (postRequest.body().isEmpty() || postRequest.title().isEmpty()) {
            throw new EntityPropertiesException("specify all properties");
        }
        if (channelRepository.findById(postRequest.ownerChannelId()).isEmpty()) {
            throw new EntityPropertiesException("invalid id for owner channel id");
        }
    }

    public void validateMessage(MessageRequest request) {
        if (chatRepository.findById(request.chatId()).isEmpty())
            throw new EntityPropertiesException("invalid chat id");

        if (repository.findById(request.sender()).isEmpty())
            throw new UserNotFoundException();

        if (request.text().trim().isBlank())
            throw new EntityPropertiesException("invalid message");
    }

    public void validateChat(ChatRequest chatRequest) {
        if (chatRequest.type() == PRIVATE_CHAT && chatRequest.users().size() > 2
                || chatRequest.type() == GROUP_CHAT && chatRequest.users().size() <= 2)
            throw new EntityPropertiesException("invalid properties to start chat. " +
                    "Check amount of users");

    }

   /* public void validateAuthenticationRequest(AuthenticationRequest authenticationRequest) {
        if (authenticationRequest.email().trim().isBlank() ||
                authenticationRequest.password().trim().isBlank())
            throw new AuthenticationException("invalid properties");

        var user = repository.findUserByEmail(authenticationRequest.email());
        if (user.isEmpty())
            throw new  AuthenticationException("invalid properties");
    }*/
}
