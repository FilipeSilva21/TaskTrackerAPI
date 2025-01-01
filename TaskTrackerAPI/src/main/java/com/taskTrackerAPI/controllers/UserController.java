package com.taskTrackerAPI.controllers;

import com.taskTrackerAPI.DTOs.CreateUserDTO;
import com.taskTrackerAPI.DTOs.UpdateUserDTO;
import com.taskTrackerAPI.entities.User;
import com.taskTrackerAPI.infra.TokenGenerator;
import com.taskTrackerAPI.services.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenGenerator tokenGenerator;

    @PostMapping
    public ResponseEntity<Map<String, String>> createUser(@RequestBody CreateUserDTO createUserDTO){

        var userId = userService.createUser(createUserDTO);

        String token = tokenGenerator.generateToken(userId);

        Map<String, String> response= new HashMap<>();
        response.put("userId", userId.toString());
        response.put("token", token);

        return ResponseEntity.created(URI.create("/v1/users/" + userId)).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<User>> listUsers(){

        var users = userService.listUsers();

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity <User> findUserById(@PathVariable ("userId ") Long userId) throws Exception {

        var user = userService.findUserById(userId);


        if (user.isPresent()){
            return ResponseEntity.ok(user.get());
        } else {
            throw new Exception("Usuario não encontrado");
        }
    }


    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable ("userId") Long userId, @RequestBody UpdateUserDTO updateUserDTO) throws Exception {


        userService.updateUser(userId, updateUserDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable ("userId") Long userId) throws Exception {

        userService.deleteUser(userId);

        return ResponseEntity.ok().build();
    }
}
