package com.example.hellospring.controller;

import com.example.hellospring.model.Task;
import com.example.hellospring.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> list(){
        return taskService.findAll();
    }
    
    public static class CreateTaskRequest {
        public String title;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody CreateTaskRequest request) {
        if(request== null || request.title==null || request.title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }
        return taskService.create(request.title.trim());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }
}
