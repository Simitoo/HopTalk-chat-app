package com.hop_talk_api.HopTalk_api.repositories;

import com.hop_talk_api.HopTalk_api.entities.ChannelParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChannelParticipantRepo extends JpaRepository<ChannelParticipant, Integer> {

    @Query("SELECT cp FROM ChannelParticipant cp " +
            "WHERE cp.groupChannel.id = :groupChannelId " +
            "AND cp.user.id = :userId " +
            "AND cp.isActive = true")
    ChannelParticipant findByGroupChannelIdAndUserIdAndIsActiveTrue(@Param("groupChannelId") int groupChannelId,
                                                                    @Param("userId") int userId);
}
