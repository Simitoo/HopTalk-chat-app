package com.hop_talk_api.HopTalk_api.repositories;

import com.hop_talk_api.HopTalk_api.entities.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Integer> {

    @Query("SELECT m FROM Message m WHERE m.receiverId = :receiverId AND m.receiverType = 'USER' AND m.isActive = true ORDER BY m.createdAt DESC")
    List<Message> findAllForDirectChannel(@Param("receiverId") int receiverId, Pageable pageable);

    @Query("SELECT m FROM Message m WHERE m.receiverId = :receiverId AND m.receiverType = 'CHANNEL' AND m.isActive = true ORDER BY m.createdAt DESC")
    List<Message> findAllForGroupChannel(@Param("receiverId") int receiverId, Pageable pageable);

    Message findMessageById(int id);
}

