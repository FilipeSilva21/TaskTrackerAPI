package com.taskTrackerAPI.DTOs;

public record CreateTaskDTO(String name, String description, Boolean isDone, Long userId) {
}
