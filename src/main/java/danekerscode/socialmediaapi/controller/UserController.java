package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.payload.request.FriendAction;
import danekerscode.socialmediaapi.payload.request.StatusUpdateRequest;
import danekerscode.socialmediaapi.payload.request.UserRequest;
import danekerscode.socialmediaapi.payload.response.ErrorResponse;
import danekerscode.socialmediaapi.service.interfaces.FriendService;
import danekerscode.socialmediaapi.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.UnknownServiceException;
import org.springframework.security.access.AccessDeniedException;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;
    private final FriendService friendService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) throws UnknownServiceException {
        return ResponseEntity.ok(userService.getById(id).orElseThrow(UnknownServiceException::new));
    }

    @GetMapping("visit/{id}")
    public ResponseEntity<?> getVisitPage(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getPageToVisit(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        userService.deleteByID(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UserRequest userRequest,
            @PathVariable("id") Integer id) {
        userService.update(userRequest, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("friend/action")
    ResponseEntity<?> friendAction(@RequestBody FriendAction friendAction) {
        friendService.doFinalAction(friendAction);
        return ResponseEntity.ok(friendAction);
    }

    @GetMapping("friend/all/{id}")
    public ResponseEntity<?> getFriends(@PathVariable Integer id) {
        return ResponseEntity.ok(friendService.getFriendListByUserId(id));
    }

    @GetMapping("block/all/{id}")
    public ResponseEntity<?> getBlackList(@PathVariable Integer id) {
        return ResponseEntity.ok(friendService.getBlackListByUserId(id));
    }

    @GetMapping("status/{firstUserId}/{secondUserId}")
    public ResponseEntity<?> userStatus(@PathVariable Integer firstUserId,
            @PathVariable Integer secondUserId) {
        return ResponseEntity.ok(
                friendService.getUserStatus(firstUserId, secondUserId));
    }

    @PutMapping("update/status/{id}")
    public ResponseEntity<?> updateStatus(@RequestBody StatusUpdateRequest request,
            @PathVariable Integer id) {
        this.userService.updateStatus(request, id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleException(AccessDeniedException e) {
        System.out.println("hello world");
        return new ResponseEntity<>(
                new ErrorResponse(
                        e.getMessage()),
                HttpStatus.UNAUTHORIZED);
    }

}
