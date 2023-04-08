package danekerscode.socialmediaapi.payload.request;


import danekerscode.socialmediaapi.model.Address;

public record UserRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        Integer age,
        String gender,
        Address address) implements Request {
}


