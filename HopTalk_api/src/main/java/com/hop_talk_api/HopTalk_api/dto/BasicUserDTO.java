package com.hop_talk_api.HopTalk_api.dto;

public class BasicUserDTO {
    private String username;
    private String iconUrl;

    public BasicUserDTO(String username, String iconUrl){
        this.username = username;
        this.iconUrl = iconUrl;
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

    public void setIconUrl(String icon_url) {
        this.iconUrl = icon_url;
    }
}
