package danekerscode.socialmediaapi.payload.request;

public record AddressRequest(String country,
                             String city,
                             String street,
                             Integer houseNumber) implements Request {
}
