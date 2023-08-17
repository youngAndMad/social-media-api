package danekerscode.socialmediaapi.payload.request;

import danekerscode.socialmediaapi.constants.ChatType;

public record ChatDTO(
        ChatType type,
        Integer firstUserId,
        Integer secondUserId
) {
}
