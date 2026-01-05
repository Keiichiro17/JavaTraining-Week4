package com.example.hellospring.model;

public class Task {
    public long id;
    public String title;

    public Task(){
    }

    public Task(long id, String title) {
        this.id = id;
        this.title = title;
    }
}
