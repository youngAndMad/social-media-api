package danekerscode.socialmediaapi.payload.request;

public record UserRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        Integer age,
        String gender,
        Boolean isPrivateAccount,
        AddressRequest address) implements Request {
}


