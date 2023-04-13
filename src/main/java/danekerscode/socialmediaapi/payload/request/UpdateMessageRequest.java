package danekerscode.socialmediaapi.payload.request;

public record UpdateMessageRequest(Integer id , String updatedMessage) implements Request{
}
