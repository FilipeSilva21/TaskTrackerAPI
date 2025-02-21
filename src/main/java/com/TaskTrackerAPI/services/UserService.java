package com.TaskTrackerAPI.services;

import com.TaskTrackerAPI.DTOs.CreateUserDTO;
import com.TaskTrackerAPI.DTOs.UpdateUserDTO;
import com.TaskTrackerAPI.entities.User;
import com.TaskTrackerAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    public Long createUser (CreateUserDTO createUserDTO){

        //convert DTO para entity
        var user = new User(
            null,
            createUserDTO.name(),
            createUserDTO.email(),
            createUserDTO.password(),
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
                user.setPassword(updateUserDTO.password());
            }

        userRepository.save(user);
        System.out.println("Usuario atualizado com sucesso");
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
