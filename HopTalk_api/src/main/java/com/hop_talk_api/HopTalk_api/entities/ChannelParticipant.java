package com.hop_talk_api.HopTalk_api.entities;

import com.hop_talk_api.HopTalk_api.constants.ChannelParticipantRole;
import jakarta.persistence.*;

@Entity
@Table(name = "channels_participants")
public class ChannelParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    private GroupChannel groupChannel;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChannelParticipantRole role = ChannelParticipantRole.PARTICIPANT;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GroupChannel getGroupChannel() {
        return groupChannel;
    }

    public void setGroupChannel(GroupChannel groupChannel) {
        this.groupChannel = groupChannel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ChannelParticipantRole getRole() {
        return role;
    }

    public void setRole(ChannelParticipantRole role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
