package danekerscode.socialmediaapi.payload.request;

import danekerscode.socialmediaapi.constants.ChatType;

import java.util.*;

public record ChatRequest(ChatType type ,
                          List<Integer> users) implements Request {
}
