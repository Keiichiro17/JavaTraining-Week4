package com.example.taskapp.controller;

import com.example.taskapp.entity.Task;
import com.example.taskapp.exception.TaskNotFoundException;
import com.example.taskapp.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskRestController {

    private final TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    // 全件取得: GET /api/tasks
    @GetMapping
    public List<Task> findAll() {
        return taskService.findAll();
    }

    // 1件取得: GET /api/tasks/{id}
    @GetMapping("/{id}")
    public Task findById(@PathVariable Long id) {
        return taskService.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    // 登録: POST /api/tasks
    @PostMapping
    public Task create(@Valid @RequestBody Task task) {
        return taskService.save(task);
    }

    // 更新: PUT /api/tasks/{id}
    @PutMapping("/{id}")
    public Task update(@PathVariable Long id, @Valid @RequestBody Task task) {
        return taskService.update(id, task);
    }

    // 削除: DELETE /api/tasks/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

    // 完了状態の切り替え: POST /api/tasks/{id}/complete
    @PostMapping("/{id}/complete")
    public Task toggleCompleted(@PathVariable Long id) {
        return taskService.toggleCompleted(id);
    }
}