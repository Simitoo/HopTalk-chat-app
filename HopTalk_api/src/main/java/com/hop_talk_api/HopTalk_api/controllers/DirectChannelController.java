package com.hop_talk_api.HopTalk_api.controllers;

import com.hop_talk_api.HopTalk_api.dto.DirectChannelDTO;
import com.hop_talk_api.HopTalk_api.entities.DirectChannel;
import com.hop_talk_api.HopTalk_api.http.AppResponse;
import com.hop_talk_api.HopTalk_api.services.DirectChannelService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DirectChannelController {

    private final DirectChannelService directChannelService;

    public DirectChannelController(DirectChannelService directChannelService){
        this.directChannelService = directChannelService;
    }

    @PostMapping("/direct")
    public ResponseEntity<?> createDirectChannel(@RequestParam String userUsername, @RequestParam String participantUsername){
        if(this.directChannelService.createDirectChannel(userUsername, participantUsername)){
            return AppResponse.success()
                    .withMessage("Direct Channel created successfully")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Cannot create this Direct Channel")
                .build();
    }

    @GetMapping("/direct/{username}")
    public ResponseEntity<?> getAllForCurrentUser(@PathVariable String username){
        List<DirectChannelDTO> directChannels = this.directChannelService.fetchAllForCurrUser(username);

        return AppResponse.success()
                .withData(directChannels)
                .build();
    }
}
