package com.hop_talk_api.HopTalk_api.controllers;

import com.hop_talk_api.HopTalk_api.dto.MessageDTO;
import com.hop_talk_api.HopTalk_api.entities.Message;
import com.hop_talk_api.HopTalk_api.http.AppResponse;
import com.hop_talk_api.HopTalk_api.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @PostMapping("/messages")
    public ResponseEntity<?> createMessage(@RequestBody Message message){
        if(this.messageService.createMessage(message)){
            return AppResponse.success()
                    .withMessage("Message created successfully")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Cannot create a message")
                .build();
    }

    @GetMapping("/messages/direct/{receiverId}")
    public ResponseEntity<?> getDirectMessages(@PathVariable int receiverId){
        List<MessageDTO> messages = this.messageService.fetchAllForDirectChannel(receiverId);

        return AppResponse.success()
                .withData(messages)
                .build();
    }

    @GetMapping("/messages/channel/{receiverId}")
    public ResponseEntity<?> getGroupMessages(@PathVariable int receiverId){
        List<MessageDTO> messages = this.messageService.fetchAllForGroupChannel(receiverId);

        return AppResponse.success()
                .withData(messages)
                .build();
    }

}
