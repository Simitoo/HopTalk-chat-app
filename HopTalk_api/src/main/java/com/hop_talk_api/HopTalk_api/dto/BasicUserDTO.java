package com.hop_talk_api.HopTalk_api.dto;

public class BasicUserDTO {
    private String username;
    private String icon_url;

    public BasicUserDTO(String username, String icon_url){
        this.username = username;
        this.icon_url = icon_url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }
}
