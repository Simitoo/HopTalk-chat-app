package com.hop_talk_api.HopTalk_api.dto;

import com.hop_talk_api.HopTalk_api.entities.DirectChannel;
import com.hop_talk_api.HopTalk_api.entities.User;

public class DirectChannelDTO {
    private int id;
    private String participantUsername;
    private String participantIconUrl;

    public DirectChannelDTO(DirectChannel directChannel, User currUser){

        User participant = (directChannel.getUser().equals(currUser)) ? directChannel.getParticipant() : directChannel.getUser();

        this.id = directChannel.getId();
        this.participantUsername = participant.getUsername();
        this.participantIconUrl = participant.getIconUrl();
    }

    public int getId() {
        return id;
    }

    public String getParticipantUsername() {
        return participantUsername;
    }

    public String getParticipantIconUrl() {
        return participantIconUrl;
    }
}
