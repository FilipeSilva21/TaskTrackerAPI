package com.TaskTrackerAPI.repositories;

import com.TaskTrackerAPI.entities.Task;
import com.TaskTrackerAPI.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {

    User findByEmail (String email);
}

