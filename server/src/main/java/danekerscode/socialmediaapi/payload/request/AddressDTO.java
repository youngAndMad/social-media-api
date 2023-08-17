package danekerscode.socialmediaapi.payload.request;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record AddressDTO(
        @NotEmpty
        String city,
        @NotEmpty
        String country,
        @NotEmpty
        String street,
        @NotNull
        Integer houseNumber
) {
}
