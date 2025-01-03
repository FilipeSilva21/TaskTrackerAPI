package com.taskTrackerAPI.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig {

    @Bean(name = "passwordEncoderWebSecurity")
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
