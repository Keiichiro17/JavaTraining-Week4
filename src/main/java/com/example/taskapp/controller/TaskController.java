package com.example.taskapp.controller;

import com.example.taskapp.entity.Task;
import com.example.taskapp.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//タスク管理APIのコントローラー

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //タスク一覧の取得（Get/api/tasks)
    @GetMapping
    public List<Task> getAll() {
        return taskService.findAll();
    }

    //タスクの新規登録（Post/api/tasks)
    @PostMapping
    public Task createTask(@Valid @RequestBody Task task) {
        return taskService.save(task);
    }

    //タスクの更新（Put/api/tasks/{id}）
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @Valid @RequestBody Task task) {
        return taskService.update(id,task);
    }

    //タスクの削除（Delete/api/tasks/{id})
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.delete(id);
    }
}
