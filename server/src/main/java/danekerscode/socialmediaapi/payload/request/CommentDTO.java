package danekerscode.socialmediaapi.payload.request;

import lombok.NonNull;

public record CommentDTO(
        @NonNull
        String comment ,
        Integer postId ,
        Integer senderId
){}
