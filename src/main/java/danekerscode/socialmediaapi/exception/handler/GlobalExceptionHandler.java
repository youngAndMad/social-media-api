package danekerscode.socialmediaapi.exception.handler;

import danekerscode.socialmediaapi.exception.AuthenticationException;
import danekerscode.socialmediaapi.exception.RegistratoinException;
import danekerscode.socialmediaapi.exception.UserNotFoundException;
import danekerscode.socialmediaapi.payload.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RegistratoinException.class)
    public ResponseEntity<ErrorResponse> handleException(RegistratoinException e) {
        return finalHandlerException(e);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleException(AuthenticationException e) {
        return finalHandlerException(e);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(UserNotFoundException e) {
        return finalHandlerException(e);
    }

    private ResponseEntity<ErrorResponse> finalHandlerException(RuntimeException e) {
        return new ResponseEntity<>(
                new ErrorResponse(
                        e.getMessage()
                ), HttpStatus.BAD_REQUEST
        );
    }
}
