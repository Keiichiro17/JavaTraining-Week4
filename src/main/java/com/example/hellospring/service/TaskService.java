package com.example.hellospring.service;

import com.example.hellospring.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong idgen= new AtomicLong(1);

public List<Task> findAll() {
        return tasks;
    }

    public Task create(String title) {
        long id = idgen.getAndIncrement();
        Task task = new Task(id,title);
        tasks.add(task);
        return task;
    }
}


