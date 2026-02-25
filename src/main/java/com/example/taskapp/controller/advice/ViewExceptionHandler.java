package com.example.taskapp.controller.advice;

import com.example.taskapp.exception.TaskNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
public class ViewExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public String handleTaskNotFound() {
        return "error/404"; // エラーの場合404.htmlを表示する
    }
}