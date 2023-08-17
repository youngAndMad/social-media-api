package danekerscode.socialmediaapi.payload.request;

import danekerscode.socialmediaapi.constants.ChannelContent;
import lombok.NonNull;

public record ChannelDTO(
        @NonNull
        String name,
        @NonNull
        ChannelContent content,
        @NonNull
        String description,
        Integer owner
) {
}
