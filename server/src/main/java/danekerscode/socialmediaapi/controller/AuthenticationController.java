package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.exception.AuthenticationException;
import danekerscode.socialmediaapi.jwt.JWTUtil;
import danekerscode.socialmediaapi.payload.request.AuthenticationDTO;
import danekerscode.socialmediaapi.payload.request.PasswordUpdateDTO;
import danekerscode.socialmediaapi.payload.request.UserDTO;
import danekerscode.socialmediaapi.payload.response.CustomResponse;
import danekerscode.socialmediaapi.service.KafkaService;
import danekerscode.socialmediaapi.service.UserService;
import danekerscode.socialmediaapi.validate.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    private final JWTUtil util;

    @PostMapping("registration")
    public ResponseEntity<?> registration(@RequestBody UserDTO request) {

        var userId = this.userService.save(request).getId();

        return new ResponseEntity<>(
                CustomResponse.builder()
                        .timeStamp(now())
                        .data(userService.createTokenResponse(request.email(), userId))
                        .message("user id:" + userId)
                        .reason("User has been successfully registered")
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
            @RequestBody PasswordUpdateDTO passwordUpdateDTO) {
        userService.updatePassword(passwordUpdateDTO);
        return new ResponseEntity<>(ACCEPTED);
    }

    @PostMapping("authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationDTO authenticationDTO) {
        return ResponseEntity.ok(userService.authenticate(authenticationDTO));
    }

}
