package com.taskTrackerAPI.services;

import com.taskTrackerAPI.DTOs.CreateTaskDTO;
import com.taskTrackerAPI.DTOs.UpdateTaskDTO;
import com.taskTrackerAPI.DTOs.UpdateUserDTO;
import com.taskTrackerAPI.entities.Task;
import com.taskTrackerAPI.entities.User;
import com.taskTrackerAPI.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Long createTask(CreateTaskDTO createTaskDTO, User user){

        var task = new Task(
            1L,
            createTaskDTO.name(),
            createTaskDTO.description(),
            createTaskDTO.isDone(),
            user
        );

        var taskSaved = taskRepository.save(task);

        return taskSaved.getTaskId();
    }


    public List<Task> listTasks(){

        return taskRepository.findAll();
    }

    public Optional<Task> findTaskById(Long taskId){

        return taskRepository.findById(taskId);
    }

    public void updateTask(Long taskId, UpdateTaskDTO updateTaskDTO){

        var taskEntity = taskRepository.findById(taskId);

        if (taskEntity.isPresent()) {
            var user = taskEntity.get();

            if (updateTaskDTO.name() != null) {
                user.setName(updateTaskDTO.name());
            }

            if (updateTaskDTO.description() != null) {
                user.setDescription(updateTaskDTO.description());
            }

            if (updateTaskDTO.isDone() != null) {
                user.setIsDone(updateTaskDTO.isDone());
            }

            taskRepository.save(user);
            System.out.println("Atividade atualizada com sucesso");
        }

    }

    public void deleteTask(Long taskId) throws Exception {

        var taskExists = taskRepository.existsById(taskId);

        if (taskExists){
            taskRepository.deleteById(taskId);
            System.out.println("Tarefa deletada com sucesso");
        } else {
            throw new Exception("Tarefa não existe");
        }
    }
}
