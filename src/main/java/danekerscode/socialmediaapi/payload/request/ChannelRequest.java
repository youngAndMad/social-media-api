package danekerscode.socialmediaapi.payload.request;

public record ChannelRequest(
        String name,
        String description,
        String content,
        Integer owner) implements Request{
}
