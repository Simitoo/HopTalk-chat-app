package com.hop_talk_api.HopTalk_api.controllers;

import com.hop_talk_api.HopTalk_api.dto.BasicGroupChannelDTO;
import com.hop_talk_api.HopTalk_api.dto.GroupChannelDTO;
import com.hop_talk_api.HopTalk_api.http.AppResponse;
import com.hop_talk_api.HopTalk_api.services.GroupChannelService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GroupChannelController {

    private GroupChannelService groupChannelService;

    public GroupChannelController(GroupChannelService groupChannelService){
        this.groupChannelService = groupChannelService;
    }

    @PostMapping("/groups")
    public ResponseEntity<?> createGroupChannel(@RequestBody GroupChannelDTO groupChannel){

        System.out.println("Channel Id: " + groupChannel.getId());
        System.out.println("Channel Title: " + groupChannel.getTitle());
        System.out.println("Channel Creator id: " + groupChannel.getCreatorId());


        if (this.groupChannelService.addGroupChannel(groupChannel)){
            return AppResponse.success()
                    .withMessage("The Group Channel has been created!")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Failed to create group channel.")
                .build();
    }

    @GetMapping("/groups/{userId}")
    public ResponseEntity<?> getAllGroupsByUserId(@PathVariable int userId){
        List<GroupChannelDTO> groupChannel = this.groupChannelService.fetchAllGroupsByUserId(userId);

        if(groupChannel != null){
            return AppResponse.success()
                    .withData(groupChannel)
                    .build();
        }

        return AppResponse.error()
                .withMessage("Group Channels are not found")
                .build();
    }

    @PostMapping("/groups/participants/{userId}")
    public ResponseEntity<?> addParticipantToGroupChannel(@PathVariable int userId, @RequestParam int channelId, @RequestParam int participantId){
        if(this.groupChannelService.addParticipant(userId, channelId, participantId)){
            return AppResponse.success()
                    .withMessage("The participant is now part of your channel")
                    .build();
        }

        return AppResponse.error()
                .withMessage("User don't have the permission to add participants.")
                .build();
    }

    @PutMapping("/groups/participants/{userId}")
    public ResponseEntity<?> updateParticipantRoleToAdmin(@PathVariable int userId, @RequestParam int channelId, @RequestParam int participantId){
        if(this.groupChannelService.giveParticipantAdminRole(channelId, userId, participantId)){
            return AppResponse.success()
                    .withMessage("User become Admin of this Group Channel.")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Only owner can change participants rights!")
                .build();
    }

    @PutMapping("/groups/{userId}")
    public ResponseEntity<?> updateGroupChannel(@PathVariable int userId, @RequestParam int channelId, @RequestBody BasicGroupChannelDTO channelData){
        System.out.println("Endpoint hit with userId: " + userId + ", channelId: " + channelId + ", channel Title: " + channelData.getTitle() + ", channel IconUrl: " + channelData.getIconUrl() );
        if(this.groupChannelService.updateGroupChannel(userId, channelId, channelData)){
            return AppResponse.success()
                    .withMessage("Group channel is updated successfully!")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Only user with Owner or Admin rights can update the Channel")
                .build();
    }

    @DeleteMapping("/groups/participants/{userId}")
    public ResponseEntity<?> removeParticipantFromGroup(@PathVariable int userId, @RequestParam int channelId, @RequestParam int participantId){
        if(this.groupChannelService.removeParticipant(userId, channelId, participantId)){
            return AppResponse.success()
                    .withMessage("The participant is removed successfully")
                    .build();
        }

        return AppResponse.error()
                .withMessage("User don't have the permission to remove other users.")
                .build();
    }

    @DeleteMapping("/groups/{userId}")
    public ResponseEntity<?> deleteGroupChannel(@PathVariable int userId, @RequestParam int channelId){
        if(this.groupChannelService.removeGroupChannel(channelId, userId)){
            return AppResponse.success()
                    .withMessage("Group channel is deleted.")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Only owner can delete a Group Channel!")
                .build();
    }
}
