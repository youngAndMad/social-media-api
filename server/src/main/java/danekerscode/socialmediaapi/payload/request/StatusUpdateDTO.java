package danekerscode.socialmediaapi.payload.request;

import lombok.NonNull;

public record StatusUpdateDTO(
        @NonNull
        String status
) {

}
