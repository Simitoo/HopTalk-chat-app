package com.hop_talk_api.HopTalk_api.controllers;

import com.hop_talk_api.HopTalk_api.dto.BasicUserDTO;
import com.hop_talk_api.HopTalk_api.dto.UserDTO;
import com.hop_talk_api.HopTalk_api.entities.User;
import com.hop_talk_api.HopTalk_api.http.AppResponse;
import com.hop_talk_api.HopTalk_api.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> fetchAllUsers(){
        List<BasicUserDTO> collection = this.userService.fetchAllUsersForSelection();

        return AppResponse.success()
                .withData(collection)
                .build();
    }

    @GetMapping("/users/search")
    public ResponseEntity<?> searchUsersByUsername(@RequestParam String username){
        List<BasicUserDTO> users = this.userService.searchUsersByUsername(username);

        if(users != null){
            return AppResponse.success()
                    .withData(users)
                    .build();
        }

        return AppResponse.error()
                .withMessage("No users found")
                .build();
    }

    @PostMapping("/users/register")
    public ResponseEntity<?> registerNewUser(@RequestBody User user){

        if(this.userService.createUser(user)){
            return AppResponse.success()
                    .withMessage("User created successfully")
                    .build();
        }

        return AppResponse.error()
                .withMessage("User with the same username exists")
                .build();
    }

    @PostMapping("/users/login")
    public ResponseEntity<?> loginUser(@RequestBody HashMap<String, String> credentials){
        String username = credentials.get("username");
        String password = credentials.get("password");

        UserDTO user = this.userService.getUserDataByUsernameAndPassword(username,password);
        if(user != null){
            return  AppResponse.success()
                    .withDataAsArray(user)
                    .withMessage("Login successful")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Invalid username or password")
                .build();
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<?> fetchBasicUserInfoByUsername(@PathVariable String username){
        BasicUserDTO responseUser = this.userService.fetchBasicUserDataByUsername(username);

        if(responseUser != null){
            return AppResponse.success()
                    .withDataAsArray(responseUser)
                    .build();
        }

        return AppResponse.error()
                .withMessage("User data is not found")
                .build();
    }

    @PutMapping("/users")
    public ResponseEntity<?> updateUserInfo(@RequestBody User user){
        System.out.println("Received user: " + user);
        if(this.userService.updateUser(user)){
            return AppResponse.success()
                    .withMessage("User is updated!")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Such user doesn't exist! Go and create one!")
                .build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> removeUser(@PathVariable int id){
        if(this.userService.removeUser(id)){
            return AppResponse.success()
                    .withMessage("User is gone :(")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Such user doesn't exist!")
                .build();
    }

    @PostMapping("/users/{username}/friends")
    public ResponseEntity<?> addFriendToUsersFriendsList(@PathVariable String username, @RequestBody BasicUserDTO friendUsername ){
        if(this.userService.addUserToFriendsList(username, friendUsername.getUsername())){
            return AppResponse.success()
                    .withMessage("Friend added successfully.")
                    .build();

        }
        return AppResponse.error()
                .withMessage("Failed to add friend. Either user or friend doesn't exist.")
                .build();
    }

    @DeleteMapping("/users/{username}/friends")
    public ResponseEntity<?> removeFriendFromFriendsList(@PathVariable String username, @RequestParam String friendUsername){
        if(this.userService.removeFriendFromFriendsList(username, friendUsername)){
            return AppResponse.success()
                    .withMessage("Friend is removed!")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Failed to remove friend. Either user or friend doesn't exist.")
                .build();
    }


}
