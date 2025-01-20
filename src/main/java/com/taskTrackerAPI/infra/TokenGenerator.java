package com.taskTrackerAPI.infra;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.StreamCorruptedException;
import java.util.Date;

import com.taskTrackerAPI.services.UserService;
import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {

    private String secretKey = "mySecretKey123";

    public String generateToken(String name) throws StreamCorruptedException {
        return Jwts.builder()
                .setSubject(String.valueOf(name))
                .setIssuedAt(new Date())
                .setExpiration(new Date())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims extractClaims(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractName (String token){
        return extractClaims(token).getSubject();
    }

    public boolean validateToken(String token, String name) {
        try {
            return name.equals(extractName(token));
        } catch (Exception e) {
            return false;
        }
    }
}
