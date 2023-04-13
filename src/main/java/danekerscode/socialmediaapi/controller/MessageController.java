package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.model.Message;
import danekerscode.socialmediaapi.payload.request.MessageRequest;
import danekerscode.socialmediaapi.service.interfaces.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("new")
    @MessageMapping("/message")
    @SendTo("/chat/message/new")
    public ResponseEntity<?> addMessage(@RequestBody MessageRequest messageRequest){
       return ResponseEntity.ok(messageService.save(messageRequest));
    }
}
