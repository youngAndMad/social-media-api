package danekerscode.socialmediaapi.payload.request;

import danekerscode.socialmediaapi.constants.ChatType;

public record ChatRequest(ChatType type ,
                          Integer firstUserId,
                          Integer secondUserId) {
}
