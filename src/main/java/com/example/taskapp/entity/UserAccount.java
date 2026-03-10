package com.example.taskapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//ユーザー情報を管理するエンティティクラス
//ユーザー名、パスワードを保存する

@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//ユーザー名、パスワード、ロールを保存するフィールド
    private String username;
    private String password;
    private String role;

    public UserAccount() {
    }
//ユーザー名、パスワード、ロールを引数に取るコンストラクタ
    public UserAccount(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
//ユーザー情報を取得・設定するためのゲッターとセッター
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
//パスワードを取得・設定するためのゲッターとセッター
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//ロールを取得・設定するためのゲッターとセッター
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
