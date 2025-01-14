package com.hop_talk_api.HopTalk_api.dto;

public class UserDTO {

    private int id;
    private String username;
    private String iconUrl;

    public UserDTO(int id, String username, String iconUrl){
        this.id = id;
        this.username = username;
        this.iconUrl = iconUrl;
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
}
