package danekerscode.socialmediaapi.controller;

import danekerscode.socialmediaapi.service.i.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageService messageService;

    
}
