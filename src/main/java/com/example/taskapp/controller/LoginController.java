package com.example.taskapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//ログインページを表示するコントローラー

@Controller
public class LoginController {
//ログインページにアクセスしたときにログイン画面を表示する
    @GetMapping("/login")
    public String login() {
        //ログインページのテンプレート名を返す
        return "login";
    }
}