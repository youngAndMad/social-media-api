package danekerscode.socialmediaapi.payload.request;

public record MessageRequest(String text,Integer chatId , Integer sender)implements Request {
}
