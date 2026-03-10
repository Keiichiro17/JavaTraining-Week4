package com.example.taskapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*Spring Securityの設定クラス
ログイン認証やアクセス制御の設定を行う*/

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/css/**", "/js/**", "/img/**", "/h2-console/**").permitAll()
                .requestMatchers("/tasks/**").authenticated()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                //カスタムログインページ
                .loginPage("/login")
                .defaultSuccessUrl("/tasks", true)
                //ログイン成功後のリダイレクトURL
                .failureUrl("/login?error")
                //ログイン失敗時のリダイレクトURL
                .permitAll()
                //ログインページへのアクセスを全てのユーザーに許可
            )
            .logout(logout -> logout
                //ログアウトURL
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
            .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}