package com.hop_talk_api.HopTalk_api.repositories;

import com.hop_talk_api.HopTalk_api.entities.GroupChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupChannelRepo extends JpaRepository<GroupChannel, Integer> {
    GroupChannel findById(int channelId);
    @Query("SELECT gc FROM GroupChannel gc LEFT JOIN FETCH gc.participants WHERE gc.id = :channelId")
    GroupChannel findByIdWithParticipants(@Param("channelId") int channelId);
}
