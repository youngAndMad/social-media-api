package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.NO_CONTENT;


@RequiredArgsConstructor
@RestController
@RequestMapping("notification")
public class NotificationController {
    private final NotificationService notificationService;

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(
            @PathVariable Integer id
    ) {
        notificationService.delete(id);
    }

    @DeleteMapping("all/{id}")
    public ResponseEntity<?> deleteAll(
            @PathVariable Integer id
    ) {
        notificationService.deleteUserNotifications(id);
        return ResponseEntity.ok(ACCEPTED);
    }

    @PutMapping("set-checked/{id}")
    public void setChecked(@PathVariable Integer id) {
        notificationService.setChecked(id);
    }
}
