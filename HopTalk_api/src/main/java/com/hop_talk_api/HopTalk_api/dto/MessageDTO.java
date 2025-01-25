package com.hop_talk_api.HopTalk_api.dto;

import com.hop_talk_api.HopTalk_api.constants.MessageType;

import java.time.LocalDateTime;

public class MessageDTO {
    private int id;
    private String senderUsername;
    private String messageContent;
    private MessageType messageType;
    private String attachmentUrl;
    private LocalDateTime createdAt;

    public MessageDTO(int id, String senderUsername, String messageContent, MessageType messageType, String attachmentUrl, LocalDateTime createdAt){
        this.id = id;
        this.senderUsername = senderUsername;
        this.messageContent = messageContent;
        this.messageType = messageType;
        this.attachmentUrl = attachmentUrl;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
