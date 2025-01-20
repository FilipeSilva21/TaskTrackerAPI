package com.taskTrackerAPI.services;

import com.taskTrackerAPI.DTOs.CreateTaskDTO;
import com.taskTrackerAPI.DTOs.UpdateTaskDTO;
import com.taskTrackerAPI.entities.Task;
import com.taskTrackerAPI.repositories.TaskRepository;
import com.taskTrackerAPI.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class TaskService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public TaskRepository taskRepository;

    public Long createTask(CreateTaskDTO createTaskDTO, Long userId){

        var user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(isNull(user.getTasks())){

            user.setTasks(new ArrayList<>());
        }

        var task = new Task(
                null,
                createTaskDTO.name(),
                createTaskDTO.description(),
                createTaskDTO.isDone()
        );

        return taskRepository.save(task).getTaskId();
    }


    public List<Task> listTasks(Long userId){

        var tasks = taskRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return tasks.user.tasks;
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
