package danekerscode.socialmediaapi.payload.request;

public record AddressDTO(
        String country,
        String city,
        String street,
        Integer houseNumber
) {
}
