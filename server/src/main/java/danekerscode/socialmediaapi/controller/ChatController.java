package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.payload.request.ChatDTO;
import danekerscode.socialmediaapi.service.impl.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static danekerscode.socialmediaapi.utils.ReturnError.validateRequest;
import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("chat")
public class ChatController {
    private final ChatServiceImpl chatService;

    @GetMapping("{id}")
    public ResponseEntity<?> getChat(
            @PathVariable Integer id
    ) {
        return ResponseEntity
                .ok(chatService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<?> createChat(
            @RequestBody @Valid ChatDTO chatDTO,
            BindingResult br
    ) {
        validateRequest(br);
        return ResponseEntity.status(201)
                .body(chatService.createChat(chatDTO));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteChat(
            @PathVariable Integer id
    ) {
        chatService.deleteById(id);
    }

    @PutMapping("leave")
    @ResponseStatus(ACCEPTED)
    public void leaveChat(
            @RequestParam(value = "chatId") Integer chatId,
            @RequestParam(value = "userId") Integer userId
    ) {
        chatService.leaveChat(userId, chatId);
    }

    @PutMapping("join")
    @ResponseStatus(ACCEPTED)
    public void joinChat(
            @RequestParam(value = "chatId") Integer chatId,
            @RequestParam(value = "userId") Integer userId
    ) {
        chatService.joinChat(userId, chatId);
    }


}
