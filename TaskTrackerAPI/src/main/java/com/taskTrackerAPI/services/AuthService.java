package com.taskTrackerAPI.services;

import com.taskTrackerAPI.entities.User;
import com.taskTrackerAPI.infra.TokenGenerator;
import com.taskTrackerAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    public TokenGenerator tokenGenerator;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

   public String login (String email, String password){

       Optional<User> userExists = Optional.ofNullable(userRepository.findByEmail(email));

       if (userExists.isPresent()){
           User user = userExists.get();

           if (passwordEncoder.matches(password, user.getPassword())){
               return tokenGenerator.generateToken(user.getEmail());
           }
       }

       return "{\"message\": \"Unauthorized\"}";
   }
}
