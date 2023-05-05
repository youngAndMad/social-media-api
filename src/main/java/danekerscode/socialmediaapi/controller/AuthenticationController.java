package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.exception.AuthenticationException;
import danekerscode.socialmediaapi.jwt.JWTUtil;
import danekerscode.socialmediaapi.payload.request.AuthenticationRequest;
import danekerscode.socialmediaapi.payload.request.EmailRequest;
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

import javax.validation.Valid;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("authentication")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final CustomValidator validator;
    private final KafkaService kafkaService;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("registration")
    public ResponseEntity<CustomResponse> registration(@RequestBody @Valid UserRequest request) {
        return new ResponseEntity<CustomResponse>(
                CustomResponse.builder()
                        .timeStamp(now())
                        .data(new TokenResponse(jwtUtil.generateToken(request.email())))
                        .message("user id:" + userService.save(request).getId())
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
            @RequestParam(name = "code", defaultValue = "") String code,
            @RequestBody String newPassword) {
        userService.updatePassword(code, newPassword);
        return new ResponseEntity<>(ACCEPTED);
    }

    @PostMapping("authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.email(),
                authenticationRequest.password());
        authenticationManager.authenticate(authenticationToken);
        return ResponseEntity.ok(new TokenResponse(jwtUtil.generateToken(authenticationRequest.email())));
    }

}
