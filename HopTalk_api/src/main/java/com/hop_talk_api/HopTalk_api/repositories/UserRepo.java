package com.hop_talk_api.HopTalk_api.repositories;

import com.hop_talk_api.HopTalk_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {
    List<User> findByIsActive(boolean isActive);
    User findUserByUsername(String username);
    User findById(int id);
}
