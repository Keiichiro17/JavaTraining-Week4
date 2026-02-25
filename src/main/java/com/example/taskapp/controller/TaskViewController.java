package com.example.taskapp.controller;

import com.example.taskapp.controller.form.TaskForm;
import com.example.taskapp.entity.Task;
import com.example.taskapp.exception.TaskNotFoundException;
import com.example.taskapp.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tasks")
public class TaskViewController {

    private final TaskService taskService;

    public TaskViewController(TaskService taskService) {
        this.taskService = taskService;
    }

    // 一覧を表示する機能(GET /tasks)
    @GetMapping
    public String index(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "tasks/index";
    }

    // 新規登録機能(GET /tasks/new)
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("taskForm", new TaskForm());
        model.addAttribute("mode", "new");
        return "tasks/form";
    }

    // 登録するための機能(POST /tasks)
    @PostMapping
    public String create(
            @Valid @ModelAttribute("taskForm") TaskForm form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes attributes
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "new");
            return "tasks/form";
        }

        Task task = new Task();
        task.setTitle(form.getTitle());
        task.setCompleted(form.isCompleted());
        taskService.save(task);

        attributes.addFlashAttribute("success", "登録しました");
        return "redirect:/tasks";
    }

    // 編集機能(GET /tasks/{id}/edit)
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Task task = taskService.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        model.addAttribute("taskForm", new TaskForm(task.getTitle(), task.isCompleted()));
        model.addAttribute("taskId", id);
        model.addAttribute("mode", "edit");
        return "tasks/form";
    }

    // 更新機能(POST /tasks/{id})
    @PostMapping("/{id}")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("taskForm") TaskForm form,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes attributes
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("taskId", id);
            model.addAttribute("mode", "edit");
            return "tasks/form";
        }

        taskService.updateFromForm(id, form.getTitle(), form.isCompleted());
        attributes.addFlashAttribute("success", "更新しました");
        return "redirect:/tasks";
    }

    // 削除機能(POST /tasks/{id}/delete)
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        taskService.delete(id);
        attributes.addFlashAttribute("success", "削除しました");
        return "redirect:/tasks";
    }

    // 完了切替機能(POST /tasks/{id}/toggle)
    @PostMapping("/{id}/toggle")
    public String toggle(@PathVariable Long id, RedirectAttributes attributes) {
        taskService.toggleCompleted(id);
        attributes.addFlashAttribute("success", "完了状態を切り替えました");
        return "redirect:/tasks";
    }
}