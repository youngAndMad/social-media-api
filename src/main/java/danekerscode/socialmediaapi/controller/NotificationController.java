package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.payload.response.CustomResponse;
import danekerscode.socialmediaapi.service.interfaces.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.ACCEPTED;


@RequiredArgsConstructor
@RestController
@RequestMapping("notification")
@CrossOrigin(origins = "http://localhost:4200")

public class NotificationController {
    private final NotificationService notificationService;

    @PutMapping("set/checked/{id}")
    public ResponseEntity<?> setChecked(@PathVariable Integer id) {
        notificationService.setChecked(id);
        return new ResponseEntity<>(
                CustomResponse.builder()
                        .status(ACCEPTED)
                        .statusCode(ACCEPTED.value())
                        .message("notification checked")
                        .timeStamp(now())
                        .build(),
                ACCEPTED
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        notificationService.delete(id);
        return ResponseEntity.ok(ACCEPTED);
    }

    @DeleteMapping("all/{id}")
    public ResponseEntity<?> deleteAll(@PathVariable Integer id){
        notificationService.deleteUserNotifications(id);
        return ResponseEntity.ok(ACCEPTED);
    }
}
