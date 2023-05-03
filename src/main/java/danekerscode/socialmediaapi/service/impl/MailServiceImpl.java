package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.payload.request.MailMessageRequest;
import danekerscode.socialmediaapi.service.interfaces.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static java.time.LocalTime.now;

@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {

    private final RestTemplate restTemplate;
    private final String URL = "http://localhost:8081/api/v1/mail";
    private final String GREETING = "/greeting";
    private final String RESET_PASSWORD = "/reset/password";

    @Override
    public void sendGreeting(String email) {
        long start = System.currentTimeMillis();
        System.out.println(now());
        customSender(email, this.GREETING);
        long end = System.currentTimeMillis();
        System.out.println("after response "+ now());
    }

    @Override
    public void sendCodeToUpdatePassword(String email) {
        customSender(email, this.RESET_PASSWORD);
    }

    public void customSender(String email, String path) {
        restTemplate.postForObject(this.URL + path, new HttpEntity<>(Map.of("email", email), headers()), HttpStatus.class);
    }

    @Override
    public void send(MailMessageRequest request) {

        restTemplate.postForObject(
                this.URL + "/send",
                new HttpEntity<>(
                        Map.of("to", request.to(),
                                "subject", request.subject(),
                                "message", request.message()),
                        headers()),
                HttpStatus.class);
    }

    private HttpHeaders headers() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }


}
