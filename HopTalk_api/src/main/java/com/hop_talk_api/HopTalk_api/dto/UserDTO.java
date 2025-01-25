package com.hop_talk_api.HopTalk_api.dto;

import java.util.List;

public class UserDTO {

    private int id;
    private String username;
    private String iconUrl;
    private List<BasicUserDTO> friendsList;
    //friends, direct and group channels

    public UserDTO(int id, String username, String iconUrl, List<BasicUserDTO> friendsList){
        this.id = id;
        this.username = username;
        this.iconUrl = iconUrl;
        this.friendsList = friendsList;
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

    public List<BasicUserDTO> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(List<BasicUserDTO> friendsList) {
        this.friendsList = friendsList;
    }
}
