package danekerscode.socialmediaapi.exception;

public class EmailRegisteredYetException extends RuntimeException {
    public EmailRegisteredYetException() {
        super("email registered yet");
    }
}
