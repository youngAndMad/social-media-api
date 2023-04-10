package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.payload.request.FriendAction;
import danekerscode.socialmediaapi.payload.request.UserRequest;
import danekerscode.socialmediaapi.service.i.FriendService;
import danekerscode.socialmediaapi.service.i.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final FriendService friendService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
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

    @PostMapping("friend/action")
    ResponseEntity<?> friendAction(@RequestBody FriendAction friendAction){
        friendService.doFinalAction(friendAction);
        return ResponseEntity.ok(friendAction);
    }

    @GetMapping("friend/all/{id}")
    public ResponseEntity<?> getFriends(@PathVariable Integer id){
        return ResponseEntity.ok(friendService.getFriendListByUserId(id));
    }

    @GetMapping("block/all/{id}")
    public ResponseEntity<?> getBlackList(@PathVariable Integer id){
        return ResponseEntity.ok(friendService.getBlackListByUserId(id));
    }

    @GetMapping("status/{firstUserId}/{secondUserId}")
    public ResponseEntity<?> userStatus(@PathVariable Integer firstUserId,
                                        @PathVariable Integer secondUserId){
        return ResponseEntity.ok(
                friendService.getUserStatus(firstUserId,secondUserId)
        );
    }

}
