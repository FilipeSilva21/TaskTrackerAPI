package com.taskTrackerAPI.controllers;

import com.taskTrackerAPI.services.AuthService;
import com.taskTrackerAPI.infra.TokenGenerator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.StreamCorruptedException;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenGenerator tokenGenerator;

    @PostMapping
    public String login(@RequestBody LoginRequest loginRequest) throws StreamCorruptedException {
        return authService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }
}

class LoginRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
