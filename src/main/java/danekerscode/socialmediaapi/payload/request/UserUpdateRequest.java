package danekerscode.socialmediaapi.payload.request;

public record UserUpdateRequest(String firstName,
                                String lastName,
                                Integer age,
                                String gender ,
                                Boolean privateAccount) implements Request{
}
