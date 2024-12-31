package com.taskTrackerAPI.services;

import com.taskTrackerAPI.DTOs.CreateUserDTO;
import com.taskTrackerAPI.DTOs.UpdateUserDTO;
import com.taskTrackerAPI.entities.User;
import com.taskTrackerAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Long createUser (CreateUserDTO createUserDTO){

        //convert DTO para entity
        var user = new User(
            1L,
            createUserDTO.name(),
            createUserDTO.email(),
            createUserDTO.password(),
            Instant.now(),
            null
        );

        var userSaved = userRepository.save(user);

        return userSaved.getUserId();
    }

    public void updateUser(Long userId, UpdateUserDTO updateUserDTO){

        var userEntity = userRepository.findById(userId);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (updateUserDTO.name() != null) {
                user.setName(updateUserDTO.name());
            }

            if (updateUserDTO.password() != null) {
                user.setPassword(updateUserDTO.password());
            }

            userRepository.save(user);
        }

    }

    public void deleteUser(Long userId) throws Exception {

        var userExists = userRepository.existsById(userId);

        if (userExists){
            userRepository.deleteById(userId);
        } else {
            throw new Exception("Usuario não existe");
        }
    }

}
