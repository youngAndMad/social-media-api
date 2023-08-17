package danekerscode.socialmediaapi.payload.request;

import lombok.NonNull;

public record UpdateMessageDTO(
        Integer id ,
        @NonNull
        String updatedMessage
) {
}
