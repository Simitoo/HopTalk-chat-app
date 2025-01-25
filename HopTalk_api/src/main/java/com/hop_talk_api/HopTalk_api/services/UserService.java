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

        if(!user.getIsActive()){
            return null;
        }

        return createBasicUserDto(user);
    }

    public UserDTO getUserDataByUsernameAndPassword(String username, String password){

        User user = this.userRepository.findUserByUsername(username);
        if(user != null && user.getPassword().equals(password) && user.getIsActive()){
            return createUserDto(user);
        }

        return null;
    }

    public boolean updateUser(User user){
        User existingUser = getUserById(user.getId());
        System.out.println("Received user: " + user);
        System.out.println("Received icon_url: " + user.getIconUrl());

        if(existingUser == null){
            return false;
        }

        if(!existingUser.getUsername().equals(user.getUsername())){
            if(this.userRepository.findUserByUsername(user.getUsername()) != null){
                throw new IllegalArgumentException("Username already taken");
            }

            existingUser.setUsername(user.getUsername());
        }

        existingUser.setIconUrl(user.getIconUrl());
        this.userRepository.save(existingUser);
        return true;
    }

    public boolean removeUser(int userId){
        User userToRemove = getUserById(userId);
        if(userToRemove != null){
            userToRemove.setIsActive(false);
            this.userRepository.save(userToRemove);
            return true;
        }

        return false;
    }

    public boolean addUserToFriendsList(String userUsername, String friendUsername){

        if (userUsername.equals(friendUsername)) {
            throw new IllegalArgumentException("A user cannot add themselves as a friend.");
        }

        User user = this.userRepository.findUserByUsername(userUsername);
        User friend = this.userRepository.findUserByUsername(friendUsername);

        if(user != null && friend != null){
            user.getFriends().add(friend);
            friend.getFriends().add(user);
            this.userRepository.save(user);
            this.userRepository.save(friend);
            return true;
        }

        return false;
    }

    public boolean removeFriendFromFriendsList(String username, String friendUsername){

        if (username.equals(friendUsername)) {
            throw new IllegalArgumentException("A user cannot remove themselves as a friend.");
        }

        User user = this.userRepository.findUserByUsername(username);
        User friend = this.userRepository.findUserByUsername(friendUsername);

        if(user != null && friend != null){
            user.getFriends().remove(friend);
            friend.getFriends().remove(user);

            this.userRepository.save(user);
            this.userRepository.save(friend);

            return true;
        }

        return false;
    }

    private User getUserById(int id){
        return this.userRepository.findById(id);
    }

    private UserDTO createUserDto(User user){
        return new UserDTO(user.getId(), user.getUsername(), user.getIconUrl(), user.getFriends().stream()
                .map(friend -> new BasicUserDTO(friend.getUsername(), friend.getIconUrl()))
                .toList());
    }

    private BasicUserDTO createBasicUserDto(User user){
        return new BasicUserDTO(user.getUsername(), user.getIconUrl());
    }
}
