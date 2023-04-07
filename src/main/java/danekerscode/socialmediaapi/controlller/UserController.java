package danekerscode.socialmediaapi.controlller;

import danekerscode.socialmediaapi.service.impl.UserServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(
                userService.getAll(),
                HttpStatus.OK
        );
    }

}
