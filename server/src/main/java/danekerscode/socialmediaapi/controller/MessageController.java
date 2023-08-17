package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.payload.request.MessageDTO;
import danekerscode.socialmediaapi.payload.request.UpdateMessageDTO;
import danekerscode.socialmediaapi.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("new")
    @MessageMapping("/message")
    @SendTo("/chat/message")
    public ResponseEntity<?> addMessage(@RequestBody MessageDTO messageDTO) {
        return ResponseEntity.ok(messageService.save(messageDTO));
    }

    @DeleteMapping("{id}")
    @MessageMapping("/message/delete")
    @SendTo("/chat/message")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        messageService.deleteByID(id);
        return ResponseEntity.ok("message deleted");
    }

    @PutMapping()
    @MessageMapping("/message/update")
    @SendTo("/chat/message")
    public ResponseEntity<?> update(@RequestBody UpdateMessageDTO request) {
        messageService.update(request, request.id());
        return ResponseEntity.ok("updated");
    }

}
