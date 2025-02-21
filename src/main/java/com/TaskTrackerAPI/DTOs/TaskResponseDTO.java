package com.TaskTrackerAPI.DTOs;

public record TaskResponseDTO (Long userId, String name, String description, Boolean isDone){
}
