package danekerscode.socialmediaapi.payload.request;

import danekerscode.socialmediaapi.constants.Gender;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public record UserDTO(
        @Size(min = 2)
        String lastName,
        @Size(min = 2)
        String firstName,
        @Size(min = 8)
        String password,
        @Min(16)
        Integer age,
        @Email
        String email,
        @NonNull
        Gender gender,
        String status,
        @NonNull
        Boolean isPrivateAccount,
        @NonNull
        AddressDTO address
) {
}
