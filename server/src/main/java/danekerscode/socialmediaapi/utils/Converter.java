package danekerscode.socialmediaapi.utils;

import danekerscode.socialmediaapi.payload.response.UserResponse;
import lombok.extern.slf4j.Slf4j;

import danekerscode.socialmediaapi.model.*;


@Slf4j
public class Converter {


    public static UserResponse toUserResponse(User user) {
        return UserResponse
                .builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .userStatus(null)
                .gender(user.getGender())
                .channels(user.getChannels())
                .imageURL(user.getImageUrl())
                .build();
    }

}
