package com.taskTrackerAPI.repositories;

import com.taskTrackerAPI.entities.Task;
import com.taskTrackerAPI.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {

    User findByEmail (String email);
}

