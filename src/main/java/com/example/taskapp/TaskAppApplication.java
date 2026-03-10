package com.example.taskapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.taskapp.entity.UserAccount;
import com.example.taskapp.repository.UserAccountRepository;
//Spring Bootのアプリケーションの起動クラス
@SpringBootApplication
public class TaskAppApplication {
//アプリケーションを起動するメインメソッド

    public static void main(String[] args) {
        SpringApplication.run(TaskAppApplication.class, args);
    }
    //アプリ起動時に初期ユーザを登録する処理
    //testuserが存在しない場合にのみデータベースに登録

    @Bean
    public CommandLineRunner initUsers(UserAccountRepository repository, PasswordEncoder passwordEncoder) {
        return args -> {
//既にユーザが存在するか確認

            if (repository.findByUsername("testuser").isEmpty()) {
                //初期ユーザをデータベースに保存
                repository.save(
                    new UserAccount("testuser", passwordEncoder.encode("password"), "USER")
                );
            }
        };
    }
}