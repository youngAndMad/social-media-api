package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.payload.request.ChatRequest;
import danekerscode.socialmediaapi.payload.response.CustomResponse;
import danekerscode.socialmediaapi.service.impl.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RestController
@RequestMapping("chat")
public class ChatController {
    private final ChatServiceImpl chatService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getChat(@PathVariable Integer id) {
        return ResponseEntity.ok(chatService.getById(id));
    }

    @PostMapping("/new")
    public ResponseEntity<?> createChat(@RequestBody ChatRequest chatRequest) {
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .timeStamp(now())
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .data("chat id:" + chatService.createChat(chatRequest).getId())
                        .message("chat successfully created. Chat type:" + chatRequest.type())
                        .build()
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteChat(@PathVariable Integer id) {
        chatService.deleteByID(id);
        return ResponseEntity.ok("chat by id deleted");
    }

    @PutMapping("leave")
    public ResponseEntity<?> leaveChat(@RequestParam(value = "chatId") Integer chatId,
                                       @RequestParam(value = "userId") Integer userId) {
        chatService.leaveChat(userId, chatId);
        return ResponseEntity.ok("user with id:" + userId + " leaved chat with id:" + chatId);
    }

    @PutMapping("join")
    public ResponseEntity<?> joinChat(@RequestParam(value = "chatId") Integer chatId,
                                      @RequestParam(value = "userId") Integer userId) {
        chatService.joinChat(userId , chatId);
        return ResponseEntity.ok("user with id:" + userId + " joined chat with id:" + chatId);
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(chatService.getAll());
    }

}
