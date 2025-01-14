package com.hop_talk_api.HopTalk_api.controllers;

import com.hop_talk_api.HopTalk_api.dto.UserDTO;
import com.hop_talk_api.HopTalk_api.entities.User;
import com.hop_talk_api.HopTalk_api.http.AppResponse;
import com.hop_talk_api.HopTalk_api.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<?> createNewUser(@RequestBody User user){
        HashMap<String, Object> response = new HashMap<>();

        if(this.userService.createUser(user)){
            return AppResponse.success()
                    .withMessage("User created successfully")
                    .build();
        }

        return AppResponse.error()
                .withMessage("User cannot be created")
                .build();
    }

    @GetMapping("/users")
    public ResponseEntity<?> fetchAllUsers(){
        List<UserDTO> collection = this.userService.getAllUsers();

        return AppResponse.success()
                .withData(collection)
                .build();
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<?> fetchSingleUserByUsername(@PathVariable String username){
        UserDTO responseUser = this.userService.getUserByUsername(username);

        if(responseUser != null){
            return AppResponse.success()
                    .withDataAsArray(responseUser)
                    .build();
        }

        return AppResponse.error()
                .withMessage("User data is not found")
                .build();
    }
}
