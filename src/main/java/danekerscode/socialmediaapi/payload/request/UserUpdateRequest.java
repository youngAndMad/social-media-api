package danekerscode.socialmediaapi.payload.request;

public record UserUpdateRequest(String firstName,
        String lastName,
        String status,
        Integer age,
        String gender,
        Boolean privateAccount) {
}
