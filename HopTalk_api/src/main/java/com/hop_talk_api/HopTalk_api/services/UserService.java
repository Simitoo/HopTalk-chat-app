package com.hop_talk_api.HopTalk_api.services;

import com.hop_talk_api.HopTalk_api.dto.BasicUserDTO;
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
        if(this.userRepository.findUserByUsername(userToAdd.getUsername()) != null){
            return false;
        }

        this.userRepository.save(userToAdd);
        return true;
    }

    public List<BasicUserDTO> fetchAllUsersForSelection(){
        return this.userRepository.findByIsActive(true).stream()
                .map(user -> new BasicUserDTO(user.getUsername(), user.getIconUrl()))
                .toList();
    }

    public BasicUserDTO fetchBasicUserDataByUsername(String username){

        User user = this.userRepository.findUserByUsername(username);

        return createBasicUserDto(user);
    }

    public UserDTO getUserByUsernameAndPassword(String username, String password){

        User user = this.userRepository.findUserByUsername(username);
        if(user != null && user.getPassword().equals(password)){
            return createUserDto(user);
        }

        return null;
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

    public boolean addUserToFriendsList(String userUsername, String friendUsername){
        User user = this.userRepository.findUserByUsername(userUsername);
        User friend = this.userRepository.findUserByUsername(friendUsername);

        if(user != null && friend != null){
            user.getFriendsList().add(friend);
            friend.getFriendsList().add(user);
            return true;
        }

        return false;
    }

    public List<BasicUserDTO> fetchFriendsList(int userId){
        return null;
    }

    private User getUserById(int id){
        return this.userRepository.findById(id);
    }

    private UserDTO createUserDto(User user){
        return new UserDTO(user.getUsername(), user.getIconUrl(), user.getFriendsList().stream()
                .map(friend -> new BasicUserDTO(friend.getUsername(), friend.getIconUrl()))
                .toList());
    }

    private BasicUserDTO createBasicUserDto(User user){
        return new BasicUserDTO(user.getUsername(), user.getIconUrl());
    }

}
