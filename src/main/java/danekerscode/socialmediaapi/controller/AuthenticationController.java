package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.exception.AuthenticationException;
import danekerscode.socialmediaapi.jwt.JWTUtil;
import danekerscode.socialmediaapi.model.User;
import danekerscode.socialmediaapi.payload.request.AuthenticationRequest;
import danekerscode.socialmediaapi.payload.request.EmailRequest;
import danekerscode.socialmediaapi.payload.request.PasswordRequest;
import danekerscode.socialmediaapi.payload.request.UserRequest;
import danekerscode.socialmediaapi.payload.response.CustomResponse;
import danekerscode.socialmediaapi.payload.response.TokenResponse;
import danekerscode.socialmediaapi.service.interfaces.KafkaService;
import danekerscode.socialmediaapi.service.interfaces.UserService;
import danekerscode.socialmediaapi.validate.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("authentication")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {
    private final UserService userService;
    private final CustomValidator validator;
    private final KafkaService kafkaService;

    @PostMapping("registration")
    public ResponseEntity<CustomResponse> registration(@RequestBody UserRequest request) {
        var userId = userService.save(request).getId();
        return new ResponseEntity<>(
                CustomResponse.builder()
                        .timeStamp(now())
                        .data(userService.createTokenResponse(request.email() , userId))
                        .message("user id:" + userId)
                        .reason("User has been registered successfully")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build(),
                CREATED);
    }

    @GetMapping("update/password")
    public ResponseEntity<?> forgotPassword(@RequestBody EmailRequest request) {
        String email = request.email();

        if (!validator.validateEmail(email)) {
            throw new AuthenticationException("invalid email: " + email);
        }

        kafkaService.sendEmailRequest(email, "resetPassword");

        return new ResponseEntity<>(
                CustomResponse.builder()
                        .timeStamp(now())
                        .status(ACCEPTED)
                        .statusCode(ACCEPTED.value())
                        .message("check your email and send code to update password")
                        .build(),
                ACCEPTED);
    }

    @PostMapping("update/password")
    public ResponseEntity<?> updatePassword(
            @RequestBody PasswordRequest passwordRequest) {
        userService.updatePassword(passwordRequest);
        return new ResponseEntity<>(ACCEPTED);
    }

    @PostMapping("authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(userService.authenticate(authenticationRequest));
    }

}
