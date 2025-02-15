package com.taskTrackerAPI.repositories;

import com.taskTrackerAPI.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
