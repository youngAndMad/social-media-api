package danekerscode.socialmediaapi.payload.request;

import lombok.NonNull;

public record MessageDTO(
        @NonNull
        String text,
        Integer chatId ,
        Integer sender
) {
}
