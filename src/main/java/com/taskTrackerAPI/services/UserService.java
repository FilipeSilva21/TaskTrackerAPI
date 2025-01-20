package com.taskTrackerAPI.services;

import com.taskTrackerAPI.DTOs.CreateUserDTO;
import com.taskTrackerAPI.DTOs.UpdateUserDTO;
import com.taskTrackerAPI.entities.User;
import com.taskTrackerAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Long createUser (CreateUserDTO createUserDTO){

        //convert DTO para entity
        var user = new User(
            null,
            createUserDTO.name(),
            createUserDTO.email(),
            passwordEncoder.encode(createUserDTO.password()),
            Instant.now(),
            null
        );

        var userSaved = userRepository.save(user);

        return userSaved.getUserId();
    }

    public List<User> listUsers(){

        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long userId){

        return userRepository.findById(userId);
    }

    public User findUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    public void updateUser(Long userId, UpdateUserDTO updateUserDTO){

        var userEntity = userRepository.findById(userId);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (updateUserDTO.name() != null) {
                user.setName(updateUserDTO.name());
            }

            if (updateUserDTO.password() != null) {
                user.setPassword(passwordEncoder.encode(updateUserDTO.password()));
            }

            userRepository.save(user);
        }

    }

    public void deleteUser(Long userId) throws Exception {

        var userExists = userRepository.existsById(userId);

        if (userExists) {
            userRepository.deleteById(userId);
        } else {
            throw new Exception("Usuario não existe");
        }
    }
}
