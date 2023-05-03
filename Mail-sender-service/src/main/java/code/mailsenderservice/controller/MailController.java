package code.mailsenderservice.controller;

import code.mailsenderservice.utils.EmailRequest;
import code.mailsenderservice.service.MailService;
import code.mailsenderservice.utils.MailMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/mail")
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;

    @PostMapping("greeting")
    public ResponseEntity<?> greeting(@RequestBody EmailRequest emailRequest){
        this.mailService.greeting(emailRequest);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PostMapping("reset/password")
    public ResponseEntity<?> resetPassword(@RequestBody EmailRequest emailRequest){
        this.mailService.sendCodeToUpdatePassword(emailRequest);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PostMapping("send")
    public ResponseEntity<?> send(@RequestBody MailMessageRequest messageRequest){
        this.mailService.send(messageRequest);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
