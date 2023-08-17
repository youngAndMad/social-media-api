package danekerscode.socialmediaapi.payload.request;

import lombok.NonNull;

public record PostDTO(
        @NonNull
        String title ,
        @NonNull
        String body ,
        Integer ownerChannelId
){
}
