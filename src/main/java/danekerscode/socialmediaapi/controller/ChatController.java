package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.payload.request.ChatRequest;
import danekerscode.socialmediaapi.payload.response.CustomResponse;
import danekerscode.socialmediaapi.service.i.ChatService;
import danekerscode.socialmediaapi.service.i.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RestController
@RequestMapping("chat")
public class ChatController {
    private final ChatService chatService;
    private final MessageService messageService;

    @GetMapping("{id}")
    public ResponseEntity<?> getChat(@PathVariable Integer id) {
        return ResponseEntity.ok(chatService.getById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteChat(@PathVariable Integer id) {
        chatService.deleteByID(id);
        return ResponseEntity.ok("chat by id deleted");
    }

    @PostMapping("new")
    public ResponseEntity<?> createChat(@RequestBody ChatRequest chatRequest) {
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .timeStamp(now())
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .data("chat id:" + chatService.save(chatRequest).getId())
                        .message("chat successfully created")
                        .build()
        );
    }

    @PostMapping("leave")
    public ResponseEntity<?> leaveChat(@RequestParam(value = "chatId") Integer chatId,
                                       @RequestParam(value = "userId") Integer userId) {
        chatService.leaveChat(userId, chatId);
        return ResponseEntity.ok(
                "user with id:" + userId + " leaved chat with id:" + chatId
        );
    }


}
