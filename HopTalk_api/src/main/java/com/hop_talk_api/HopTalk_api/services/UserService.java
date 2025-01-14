package com.hop_talk_api.HopTalk_api.services;

import com.hop_talk_api.HopTalk_api.dto.UserDTO;
import com.hop_talk_api.HopTalk_api.entities.User;
import com.hop_talk_api.HopTalk_api.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepository;

    public UserService(UserRepo userRepository){
        this.userRepository = userRepository;
    }

    public boolean createUser(User userToAdd){
        this.userRepository.save(userToAdd);
        //simple validation:
        //is user with the same username exist
        return true;
    }

    public List<UserDTO> getAllUsers(){
        return this.userRepository.findByIsActive(true).stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getIconUrl()))
                .toList();
    }

    public UserDTO getUserByUsername(String username){
        return this.userRepository.findByUsername(username);
        //what if => null result
    }

    public User getUserById(int id){
        return this.userRepository.findById(id);
    }

    public boolean updateUser(User user){
        this.userRepository.save(user);
        return true;
    }

    public boolean removeUser(int id){
        User userToRemove = getUserById(id);
        if(userToRemove != null){
            userToRemove.setIsActive(false);
            this.userRepository.save(userToRemove);
            return true;
        }

        return false;
    }
}
