package danekerscode.socialmediaapi.exception;

import danekerscode.socialmediaapi.payload.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RegistratoinException.class)
    public ResponseEntity<ErrorResponse> handleException(RegistratoinException e  , WebRequest request){
        return new ResponseEntity<>(
                new ErrorResponse(
                        e.getMessage()
                ) , HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleException(AuthenticationException e  , WebRequest request){
        return new ResponseEntity<>(
                new ErrorResponse(
                        e.getMessage()
                ) , HttpStatus.BAD_REQUEST
        );
    }
}
