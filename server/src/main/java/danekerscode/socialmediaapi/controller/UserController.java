package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.payload.request.FriendAction;
import danekerscode.socialmediaapi.payload.request.StatusUpdateDTO;
import danekerscode.socialmediaapi.payload.request.UserDTO;
import danekerscode.socialmediaapi.service.FriendService;
import danekerscode.socialmediaapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static danekerscode.socialmediaapi.utils.ReturnError.validateRequest;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final FriendService friendService;


    @PostMapping("registration")
    public ResponseEntity<?> registration(
            @RequestBody @Valid UserDTO request,
            BindingResult br
    ) {
        validateRequest(br);
        return ResponseEntity
                .status(201)
                .body(userService.save(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(
            @PathVariable Integer id
    ) {
        return ResponseEntity
                .ok(userService.getById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(
            @PathVariable Integer id
    ) {
        userService.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(ACCEPTED)
    public void update(
            @RequestBody @Valid UserDTO userDTO,
            BindingResult br,
            @PathVariable("id") Integer id
    ) {
        validateRequest(br);
        userService.update(userDTO, id);
    }

    @PostMapping("friend/action")
    ResponseEntity<?> friendAction(
            @RequestBody FriendAction friendAction
    ) {
        friendService.doFinalAction(friendAction);
        return ResponseEntity.ok(friendAction);
    }

    @GetMapping("{status}/all/{id}")
    public ResponseEntity<?> getUserList(
            @PathVariable String status,
            @PathVariable Integer id) {
        return ResponseEntity.ok(friendService.getUserList(status, id));
    }

    @GetMapping("status/{firstUserId}/{secondUserId}")
    public ResponseEntity<?> userStatus(
            @PathVariable Integer firstUserId,
            @PathVariable Integer secondUserId
    ) {
        return ResponseEntity
                .ok(friendService.getUserStatus(firstUserId, secondUserId));
    }

    @PutMapping("update/status/{id}")
    public ResponseEntity<?> updateStatus(
            @RequestBody StatusUpdateDTO request,
            @PathVariable Integer id
    ) {
        this.userService.updateStatus(request, id);
        return ResponseEntity.ok(ACCEPTED);
    }


}
