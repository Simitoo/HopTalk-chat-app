package com.hop_talk_api.HopTalk_api.repositories;

import com.hop_talk_api.HopTalk_api.entities.DirectChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DirectChannelRepo extends JpaRepository<DirectChannel, Integer> {
    @Query("SELECT d FROM DirectChannel d WHERE (d.user.username = :username OR d.participant.username = :username) AND d.isActive = :channelIsActive")
    List<DirectChannel> findAllForUserAndChannelIsActive(@Param("username") String username, @Param("channelIsActive") boolean channelIsActive);
    DirectChannel findByParticipantUsername(String participantUsername);
    DirectChannel findById(int id);
}
