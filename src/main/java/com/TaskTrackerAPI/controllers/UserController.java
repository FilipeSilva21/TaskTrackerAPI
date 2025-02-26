package com.TaskTrackerAPI.controllers;

import com.TaskTrackerAPI.DTOs.CreateUserDTO;
import com.TaskTrackerAPI.DTOs.UpdateUserDTO;
import com.TaskTrackerAPI.entities.User;
import com.TaskTrackerAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.StreamCorruptedException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/users")
public class    UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity <User> createUser(@RequestBody CreateUserDTO createUserDTO){

        var userId = userService.createUser(createUserDTO);

        return ResponseEntity.created(URI.create("/v1/users/" + userId)).build();
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {

        var users = userService.listUsers();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable("userId") Long userId) throws Exception {

        var user = userService.findUserById(userId);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        throw new Exception("Usuario não encontrado");
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

    /*
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody CreateUserDTO userDTO) throws StreamCorruptedException {

        User user = userService.findUserByEmail(userDTO.email());

        if (user != null && user.getPassword().matches(authService.)) {
            String token = tokenGenerator.generateToken(user.getEmail());

            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body(Map.of("message", "Unauthorized"));
    }*/

}
