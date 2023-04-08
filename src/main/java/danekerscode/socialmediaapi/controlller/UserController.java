package danekerscode.socialmediaapi.controlller;

import danekerscode.socialmediaapi.payload.request.UserRequest;
import danekerscode.socialmediaapi.service.i.UserService;
import danekerscode.socialmediaapi.validate.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(
                userService.getAll(),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        userService.deleteByID(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UserRequest userRequest,
                                    @PathVariable("id") Integer id) {
        userService.update(userRequest , id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
