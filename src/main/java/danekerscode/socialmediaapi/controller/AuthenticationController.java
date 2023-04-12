package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.exception.AuthenticationException;
import danekerscode.socialmediaapi.jwt.JWTUtil;
import danekerscode.socialmediaapi.payload.request.AuthenticationRequest;
import danekerscode.socialmediaapi.payload.request.UserRequest;
import danekerscode.socialmediaapi.payload.response.CustomResponse;
import danekerscode.socialmediaapi.service.interfaces.MailService;
import danekerscode.socialmediaapi.service.interfaces.UserService;
import danekerscode.socialmediaapi.validate.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("authentication")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final CustomValidator validator;
    private final MailService mailService;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("registration")
    public ResponseEntity<?> registration(@RequestBody @Valid UserRequest request) {
        return new ResponseEntity<>(
                CustomResponse.builder()
                        .timeStamp(now())
                        .data(Map.of(
                                "userId", userService.save(request).getId(),
                                "token", jwtUtil.generateToken(request.email()))
                        )
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
        mailService.sendCodeToUpdatePassword(email);
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

    @PostMapping("authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.email(),
                authenticationRequest.password()
        );
        authenticationManager.authenticate(authenticationToken);
        String token = jwtUtil.generateToken(authenticationRequest.email());
        return ResponseEntity.ok("token:" + token);
    }

}
