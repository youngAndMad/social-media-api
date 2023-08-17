package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.payload.request.AuthenticationDTO;
import danekerscode.socialmediaapi.payload.request.PasswordUpdateDTO;
import danekerscode.socialmediaapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("authentication")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

//
//    @GetMapping("update/password")
//    public ResponseEntity<?> forgotPassword(
//            @RequestBody EmailRequest request
//    ) {
//        String email = request.email();
//
//        if (!validator.validateEmail(email)) {
//            throw new AuthenticationException("invalid email: " + email);
//        }
//
//        kafkaService.sendEmailRequest(email, "resetPassword");
//
//        return new ResponseEntity<>(
//                CustomResponse.builder()
//                        .timeStamp(now())
//                        .status(ACCEPTED)
//                        .statusCode(ACCEPTED.value())
//                        .message("check your email and send code to update password")
//                        .build(),
//                ACCEPTED);
//    }


    @PostMapping("update/password")
    @ResponseStatus(ACCEPTED)
    public void updatePassword(
            @RequestBody PasswordUpdateDTO dto
    ) {
        userService.updatePassword(dto);
    }

    @PostMapping
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationDTO dto
    ) {
        return ResponseEntity
                .ok(userService.authenticate(dto));
    }

}
