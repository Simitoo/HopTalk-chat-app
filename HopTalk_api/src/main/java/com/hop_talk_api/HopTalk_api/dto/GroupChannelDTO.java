package com.hop_talk_api.HopTalk_api.dto;

import com.hop_talk_api.HopTalk_api.entities.GroupChannel;

import java.util.List;
import java.util.Set;

public class GroupChannelDTO {

    private int id;
    private String title;
    private int creatorId;
    private String iconUrl;
    private List<GroupParticipantDTO> participants;

    public GroupChannelDTO(int id, String title, int creatorId, String iconUrl, List<GroupParticipantDTO> participants){
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.iconUrl = iconUrl;
        this.participants = participants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public List<GroupParticipantDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<GroupParticipantDTO> participants) {
        this.participants = participants;
    }
}
