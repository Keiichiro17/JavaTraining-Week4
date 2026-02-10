package com.example.taskapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*Spring Bootアプリケーションの起動クラス
このクラスからアプリケーションが起動される */

@SpringBootApplication
public class TaskAppApplication {
    public static void main(String[] args) {
        //Spring Bootアプリケーションの起動
        SpringApplication.run(TaskAppApplication.class, args);
    }
}