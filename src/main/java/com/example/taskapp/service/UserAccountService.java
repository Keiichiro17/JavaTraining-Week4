package com.example.taskapp.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.example.taskapp.entity.UserAccount;
import com.example.taskapp.repository.UserAccountRepository;

//ユーザーアカウントのサービスクラス
//ユーザー情報を取得してSpring SecurityのUserDetailsに変換する

@Service
public class UserAccountService implements UserDetailsService {
//ユーザー名でデータベース検索
    private final UserAccountRepository userAccountRepository;

    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }
//ユーザー名をもとにユーザー情報を取得する
//Spring Securityのログイン処理で自動的に呼び出される
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUsername(username)
        //見つからない場合は例外をスロー
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new User(
                userAccount.getUsername(),
                userAccount.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + userAccount.getRole()))
        );
    }
}