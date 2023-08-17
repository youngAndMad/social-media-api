package danekerscode.socialmediaapi.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public record AuthenticationDTO(
        @Email
        String email ,
        @Size(min = 8)
        String password
) {
}
