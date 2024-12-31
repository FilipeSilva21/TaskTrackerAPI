package com.taskTrackerAPI.infra;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import com.taskTrackerAPI.services.UserService;

public class TokenGenerator {

    private static final String SECRET_KEY = "mySecretKey123";

    private UserService userService;

    public TokenGenerator(UserService userService) {
        this.userService = userService;
    }

    public static String generateToken(Long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // HS256 para assinatura
                .compact();
    }
}

