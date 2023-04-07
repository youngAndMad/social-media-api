package danekerscode.socialmediaapi.payload.request;

public record CreateChannelRequest(
        String name,
        String description,
        String content,
        Integer owner) {
}
