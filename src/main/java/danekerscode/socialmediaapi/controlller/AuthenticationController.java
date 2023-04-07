package danekerscode.socialmediaapi.controlller;

import danekerscode.socialmediaapi.exception.AuthenticationException;
import danekerscode.socialmediaapi.payload.request.RegistrationRequest;
import danekerscode.socialmediaapi.payload.response.CustomResponse;
import danekerscode.socialmediaapi.service.MailServiceImpl;
import danekerscode.socialmediaapi.service.impl.UserServiceImpl;
import danekerscode.socialmediaapi.validate.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("authentication")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserServiceImpl userService;
    private final CustomValidator validator;
    private final MailServiceImpl mailServiceImpl;

    @PostMapping("registration")
    public ResponseEntity<?> registration(@RequestBody @Valid RegistrationRequest request) {
        validator.validateRegistrationRequest(request);
        mailServiceImpl.sendGreeting(request.email());
        return new ResponseEntity<>(
                CustomResponse.builder()
                        .timeStamp(now())
                        .data(Map.of("userId", userService.save(request).getId()))
                        .message("User has been registered successfully")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build(),
                CREATED
        );
    }

    @GetMapping("update/password")
    public ResponseEntity<?> forgotPassword(@RequestBody String email) {
        if (!validator.validateEmail(email)) {
            throw new AuthenticationException("invalid email: " + email);
        }
        mailServiceImpl.sendCodeToUpdatePassword(email);
        return new ResponseEntity<>(
                CustomResponse.builder()
                        .timeStamp(now())
                        .status(ACCEPTED)
                        .statusCode(ACCEPTED.value())
                        .message("check your email and send code to update password")
                        .build()
                , OK
        );
    }

    @PostMapping("update/password")
    public ResponseEntity<?> updatePassword(
            @RequestParam(name = "code", defaultValue = "") String code,
            @RequestBody String newPassword) {
        userService.updatePassword(code, newPassword);
        return new ResponseEntity<>(ACCEPTED);
    }

}
