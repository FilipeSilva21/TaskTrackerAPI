package com.taskTrackerAPI.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.StreamCorruptedException;

@Configuration
public class AppConfig {

    @Bean(name = "passwordEncoderAppConfig")
    public PasswordEncoder passwordEncoder() throws StreamCorruptedException {
        return new BCryptPasswordEncoder();
    }
}

