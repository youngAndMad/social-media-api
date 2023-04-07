package danekerscode.socialmediaapi.payload.request;


public record RegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        Integer age,
        String gender) {
        }


