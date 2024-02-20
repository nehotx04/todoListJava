package com.api.todoList.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.api.todoList.Requests.TaskRequest;
import com.api.todoList.models.Task;
import com.api.todoList.models.User;
import com.api.todoList.repositories.TaskRepository;
import com.api.todoList.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public String createTask(@RequestBody TaskRequest request){
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if(user != null){
            Task newTask = new Task();
            newTask.setTitle(request.getTitle());
            newTask.setDescription(request.getDescription());
            newTask.setUser(user);

            taskRepository.save(newTask);
            return "Task created Successfully";
        }else{
            return "Couldnt create task because: User id not found";
        }
    }    

    @GetMapping
    public List<Task> getTasks() throws Exception{
        List<Task> users = taskRepository.findAll();
        
        if(users != null){
            return users;
        }else{
            throw new Exception("Lista de usuarios vacía");
        }
    }

    @GetMapping("/reversed/{id}")
    public List<Task> getReversed(@PathVariable Long id){
        return taskRepository.getTasksReversed(id);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) throws Exception{
        Optional<Task> task = taskRepository.findById(id);
        if(task != null){
            return task.get();
        }else{
            throw new Exception("Task not found");
        }
    }

    @PutMapping("/edit/{id}")
    public String editTask(@PathVariable Long id,@RequestBody TaskRequest request){
        Optional<Task> optionalTask = taskRepository.findById(id);
        
        User user =  userRepository.findById(request.getUserId()).orElse(null);
        if(user != null){
            optionalTask.ifPresent(task -> {
                request.setId(id);
                task.setTitle(request.getTitle());
                task.setDescription(request.getDescription());
                task.setUser(user);
                taskRepository.save(task);
            });
            return "Task Updated successfully";

        }else{
            return "Error User or Task not found";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id){
        Optional<Task> task = taskRepository.findById(id);
        if(task != null){
            taskRepository.deleteById(id);
            return "Task deleted successfully";
        }else{
            return "Task with id: " + id + " not found";
        }
    }
}
