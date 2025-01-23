package com.taskTrackerAPI.services;

import com.taskTrackerAPI.entities.User;
import com.taskTrackerAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.StreamCorruptedException;
import java.util.Optional;

@Service
public class AuthService {
/*
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

   public Boolean login (String email, String password) throws StreamCorruptedException {

       Optional<User> userExists = Optional.ofNullable(userRepository.findByEmail(email));

       if (userExists.isPresent()){
           User user = userExists.get();


           if(password.matches(user.getPassword())){

               return true;
           }
       }
       return "{\"message\": \"Unauthorized\"}";
   }*/
}
