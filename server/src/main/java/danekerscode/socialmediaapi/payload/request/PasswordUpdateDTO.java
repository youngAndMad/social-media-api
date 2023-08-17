package danekerscode.socialmediaapi.payload.request;

import javax.validation.constraints.Size;

public record PasswordUpdateDTO(
        String code ,
        @Size(min = 8)
        String newPassword
) {
}
