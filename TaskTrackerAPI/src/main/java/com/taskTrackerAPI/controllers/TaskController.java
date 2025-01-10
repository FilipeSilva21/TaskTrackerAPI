package com.taskTrackerAPI.controllers;

import com.taskTrackerAPI.DTOs.CreateTaskDTO;
import com.taskTrackerAPI.DTOs.UpdateTaskDTO;
import com.taskTrackerAPI.DTOs.UpdateUserDTO;
import com.taskTrackerAPI.entities.Task;
import com.taskTrackerAPI.entities.User;
import com.taskTrackerAPI.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskDTO createTaskDTO,
                                             @PathVariable User user){

        var taskId = taskService.createTask(createTaskDTO, user);

        return ResponseEntity.created(URI.create("/v1/tasks/" + taskId)).build();
    }

    @GetMapping
    public ResponseEntity<List<Task>> listTasks() {

        var tasks = taskService.listTasks();

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> findTaskById(@PathVariable("taskId") Long taskId) throws Exception {

        var task = taskService.findTaskById(taskId);

        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        }
        throw new Exception("Tarefa não encontrada");
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Void> updateTask(@PathVariable ("taskId") Long taskId, @RequestBody UpdateTaskDTO updateTaskDTO) throws Exception {


        taskService.updateTask(taskId, updateTaskDTO);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<User> deleteTask(@PathVariable ("taskId") Long taskId) throws Exception {

        taskService.deleteTask(taskId);

        return ResponseEntity.ok().build();
    }
}
