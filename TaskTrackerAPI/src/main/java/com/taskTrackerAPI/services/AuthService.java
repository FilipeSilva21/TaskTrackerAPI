package com.taskTrackerAPI.services;

import com.taskTrackerAPI.entities.User;
import com.taskTrackerAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User login(String email, String password) throws Exception {

        if (userService.findUserByEmail(email).isPresent()) {
            throw new Exception("Usuario com esse email ja existe");
        }

        User user = new User();
        user.setName(user.getName());
        user.setPassword(passwordEncoder.encode(password));

        return userService.createUserFromEntity(user);
    }

    public User validate(String email, String password){

        return userService.findUserByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new RuntimeException("senha incoreta"));
    }

}
