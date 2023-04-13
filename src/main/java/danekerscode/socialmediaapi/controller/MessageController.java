package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.model.Message;
import danekerscode.socialmediaapi.payload.request.MessageRequest;
import danekerscode.socialmediaapi.payload.request.UpdateMessageRequest;
import danekerscode.socialmediaapi.service.interfaces.MessageService;
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
    public ResponseEntity<?> addMessage(@RequestBody MessageRequest messageRequest){
       return ResponseEntity.ok(messageService.save(messageRequest));
    }

    @DeleteMapping("{id}")
    @MessageMapping("/message/delete")
    @SendTo("/chat/message")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        messageService.deleteByID(id);
        return ResponseEntity.ok("message deleted");
    }

    @PutMapping()
    @MessageMapping("/message/update")
    @SendTo("/chat/message")
    public ResponseEntity<?> update(@RequestBody UpdateMessageRequest request){
        messageService.update(request , request.id());
        return ResponseEntity.ok("updated");
    }

}
