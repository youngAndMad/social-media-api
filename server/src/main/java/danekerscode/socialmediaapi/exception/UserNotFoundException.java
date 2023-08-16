package danekerscode.socialmediaapi.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("User by id dont found");
    }
}
