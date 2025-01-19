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

    @PostMapping("/users/register")
    public ResponseEntity<?> createNewUser(@RequestBody User user){
        HashMap<String, Object> response = new HashMap<>();

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

        UserDTO user = this.userService.getUserByUsernameAndPassword(username,password);
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
}
