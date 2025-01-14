package com.hop_talk_api.HopTalk_api.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "direct_channels")
@Data
public class DirectChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinTable(name = "user_id")
    private User user;

    @OneToOne
    @JoinTable(name = "participant_id")
    private User participant;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "is_active")
    private boolean isActive = true;
}
