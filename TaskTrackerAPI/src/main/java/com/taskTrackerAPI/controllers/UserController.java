package com.taskTrackerAPI.controllers;

import com.taskTrackerAPI.DTOs.CreateUserDTO;
import com.taskTrackerAPI.DTOs.UpdateUserDTO;
import com.taskTrackerAPI.entities.User;
import com.taskTrackerAPI.infra.TokenGenerator;
import com.taskTrackerAPI.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private UserService userService;

    private TokenGenerator tokenGenerator;

    public UserController(UserService userService, TokenGenerator tokenGenerator) {
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createUser(@RequestBody CreateUserDTO createUserDTO){

        var userId = userService.createUser(createUserDTO);

        String token = tokenGenerator.generateToken(userId);

        Map<String, String> response= new HashMap<>();
        response.put("userId", userId.toString());
        response.put("token", token);

        return ResponseEntity.created(URI.create("/v1/users/" + userId)).body(response);
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@PathVariable ("userId") Long userId, @RequestBody UpdateUserDTO updateUserDTO){


        userService.updateUser(userId, updateUserDTO);


    }
}
