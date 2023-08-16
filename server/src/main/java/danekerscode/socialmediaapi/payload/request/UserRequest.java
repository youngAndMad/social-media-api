package danekerscode.socialmediaapi.payload.request;

public record UserRequest(
                String firstName,
                String lastName,
                String password,
                Integer age,
                String email,
                String gender,
                String status,
                Boolean isPrivateAccount,
                AddressRequest address) {
}
