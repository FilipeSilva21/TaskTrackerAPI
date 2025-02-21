package com.TaskTrackerAPI.services;

import com.TaskTrackerAPI.DTOs.CreateTaskDTO;
import com.TaskTrackerAPI.DTOs.UpdateTaskDTO;
import com.TaskTrackerAPI.entities.Task;
import com.TaskTrackerAPI.entities.User;
import com.TaskTrackerAPI.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    public TaskRepository taskRepository;

    public Long createTask(CreateTaskDTO createTaskDTO, User user){

        var task = new Task(
            null,
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
            var task = taskEntity.get();

            if (updateTaskDTO.name() != null) {
                task.setName(updateTaskDTO.name());
            }

            if (updateTaskDTO.description() != null) {
                task.setDescription(updateTaskDTO.description());
            }

            if (!updateTaskDTO.isDone()) {
                task.setDone(false);
            }

            taskRepository.save(task);
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
