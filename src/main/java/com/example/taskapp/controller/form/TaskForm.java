package com.example.taskapp.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskForm
{
    @NotBlank(message = "タイトルは必須です")
    @Size(max = 50, message = "タイトルは50文字以内で入力してください")
    private String title;

   private boolean completed;

   public TaskForm(){}

   public TaskForm(String title,boolean completed) {
       this.title = title;
       this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
