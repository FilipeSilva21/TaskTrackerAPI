package com.TaskTrackerAPI.repositories;

import com.TaskTrackerAPI.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
