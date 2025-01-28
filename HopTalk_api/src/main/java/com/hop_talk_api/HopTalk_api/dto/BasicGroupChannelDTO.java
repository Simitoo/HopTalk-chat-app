package com.hop_talk_api.HopTalk_api.dto;

public class BasicGroupChannelDTO {
    private String title;
    private String iconUrl;

    public BasicGroupChannelDTO(String title, String iconUrl){
        this.title = title;
        this.iconUrl = iconUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
