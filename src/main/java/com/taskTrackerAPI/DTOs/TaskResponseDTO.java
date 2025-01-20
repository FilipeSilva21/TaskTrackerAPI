package com.taskTrackerAPI.DTOs;

public record TaskResponseDTO (Long userId, String name, String description, Boolean isDone){
}
