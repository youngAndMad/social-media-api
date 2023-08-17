package danekerscode.socialmediaapi.payload.request;

import danekerscode.socialmediaapi.constants.ChatType;

import javax.validation.constraints.NotNull;

public record ChatDTO(
        @NotNull
        ChatType type,
        Integer firstUserId,
        Integer secondUserId
) {
}
