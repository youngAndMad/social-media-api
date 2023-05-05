package danekerscode.socialmediaapi.utils;

import danekerscode.socialmediaapi.constants.ChannelContent;
import danekerscode.socialmediaapi.constants.GENDER;
import danekerscode.socialmediaapi.model.*;
import danekerscode.socialmediaapi.payload.request.*;
import danekerscode.socialmediaapi.payload.response.UserResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import static danekerscode.socialmediaapi.constants.Role.ROLE_USER;
import static java.time.LocalDateTime.now;

@Slf4j
public class Converter {

    public static User toUser(UserRequest userRequest) {
        return User.builder()
                .firstName(userRequest.firstName())
                .lastName(userRequest.lastName())
                .email(userRequest.email())
                .gender(GENDER.valueOf(userRequest.gender().toUpperCase(Locale.ROOT)))
                .age(userRequest.age())
                .password(userRequest.password())
                .role(ROLE_USER)
                .isPrivateAccount(userRequest.isPrivateAccount() == null ? Boolean.FALSE : userRequest.isPrivateAccount())
                .address(
                        Address.builder()
                                .country(userRequest.address().country())
                                .city(userRequest.address().city())
                                .street(userRequest.address().street())
                                .houseNumber(userRequest.address().houseNumber())
                                .build()
                )
                .build();
    }

    public static void toUpdatedUser(UserUpdateRequest request, User user) {
        user.setAge(request.age());
        user.setGender(GENDER.valueOf(request.gender().toUpperCase(Locale.ROOT)));
        user.setFirstName(request.firstName());
        user.setIsPrivateAccount(request.privateAccount());
        user.setLastName(user.getLastName());
    }

    public static Channel toChannel(ChannelRequest request, User user) {
        return Channel.builder()
                .name(request.name())
                .content(ChannelContent.valueOf(request.content().toUpperCase(Locale.ROOT)))
                .description(request.description())
                .owner(user)
                .build();
    }

    public static File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File( file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
    public static Post toPost(PostRequest request, Channel channel) {
        return Post.builder()
                .title(request.title())
                .body(request.body())
                .channel(channel)
                //.images(new ArrayList<>()) fixme
                .build();
    }

    public static UserResponse toUserResponse(User user) {
        return UserResponse
                .builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .gender(user.getGender())
                .channels(user.getChannels())
                .imageURL(user.getImageUrl())
                .build();
    }

    public static Message toMessage(MessageRequest request, User user, Chat chat) {
        return Message.builder()
                .text(request.text())
                .sentAt(now())
                .senderFullName(user.getFirstName() + " " + user.getLastName())
                .sender(user)
                .chat(chat)
                .build();
    }

    public static Chat toChat(ChatRequest chatRequest, List<User> userList) {
        return Chat.builder()
                .createdAt(now())
                .type(chatRequest.type())
                .messages(new ArrayList<>())
                .users(new HashSet<>(userList))
                .lastMessage("send message to start chat")
                .build();
    }

    public static Comment toComment(CommentRequest commentRequest, User user, Post post) {
        return Comment.builder()
                .commentedAt(now())
                .comment(commentRequest.comment())
                .post(post)
                .sender(user)
                .build();
    }

    public static Notification toNotification(String body,User user){
        return Notification.builder()
                .sentAt(now())
                .message(body)
                .checked(Boolean.FALSE)
                .owner(user)
                .build();
    }
}
