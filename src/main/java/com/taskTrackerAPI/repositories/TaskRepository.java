package com.taskTrackerAPI.repositories;

import com.taskTrackerAPI.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
