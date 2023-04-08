package danekerscode.socialmediaapi.utils;

import danekerscode.socialmediaapi.model.Channel;
import danekerscode.socialmediaapi.model.Image;
import danekerscode.socialmediaapi.model.Post;
import danekerscode.socialmediaapi.model.User;
import danekerscode.socialmediaapi.model.constants.ChannelContent;
import danekerscode.socialmediaapi.model.constants.GENDER;
import danekerscode.socialmediaapi.payload.request.ChannelRequest;
import danekerscode.socialmediaapi.payload.request.PostRequest;
import danekerscode.socialmediaapi.payload.request.UserRequest;
import danekerscode.socialmediaapi.payload.request.UserUpdateRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class Converter {
    public static User toUser(UserRequest userRequest) {
        return User.builder()
                .firstName(userRequest.firstName())
                .lastName(userRequest.lastName())
                .email(userRequest.email())
                .gender(GENDER.valueOf(userRequest.gender().toUpperCase(Locale.ROOT)))
                .age(userRequest.age())
                .password(userRequest.password())
                .build();
    }

    public static void toUpdatedUser(UserUpdateRequest request, User user) {
        user.setAge(request.age());
        user.setGender(GENDER.valueOf(request.gender().toUpperCase(Locale.ROOT)));
        user.setFirstName(request.firstName());
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

    public static Image toImage(MultipartFile file){
        try {
            return Image.builder()
                    .originalName(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .bytes(file.getBytes())
                    .size(file.getSize())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Post toPost(PostRequest request , Channel channel){
        return Post.builder()
                .title(request.title())
                .body(request.body())
                .channel(channel)
                .likes(0)
                .images(new ArrayList<>())
                .build();
    }
}
