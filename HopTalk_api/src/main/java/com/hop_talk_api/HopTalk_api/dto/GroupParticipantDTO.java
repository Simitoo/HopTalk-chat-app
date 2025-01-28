package com.hop_talk_api.HopTalk_api.dto;

import com.hop_talk_api.HopTalk_api.constants.ChannelParticipantRole;

public class GroupParticipantDTO {
    private int id;
    private String username;
    private String iconUrl;
    private ChannelParticipantRole role;

    public GroupParticipantDTO(int id, String username, String iconUrl, ChannelParticipantRole role){
        this.id = id;
        this.username = username;
        this.iconUrl = iconUrl;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public ChannelParticipantRole getRole() {
        return role;
    }

    public void setRole(ChannelParticipantRole role) {
        this.role = role;
    }
}
