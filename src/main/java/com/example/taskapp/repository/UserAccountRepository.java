package com.example.taskapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskapp.entity.UserAccount;
//ユーザーアカウントを管理するリポジトリインターフェース


public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    //ユーザー名からユーザ情報を取得する
    Optional<UserAccount> findByUsername(String username);
}
