package com.example.taskapp;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

//タスクアプリの認証機能とアクセス制御を確認するテストクラス

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void ログインページは未認証でも表示できる() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
//タスク一覧ページは認証が必要であることを確認するテスト
    @Test
    void 認証なしアクセスはログインページへリダイレクト() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }
//認証されたユーザーがタスク一覧ページにアクセスできることを確認するテスト
    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void 認証ありなら一覧ページが表示される() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(view().name("tasks/index"));
    }
//正しい認証情報でログインした場合、タスク一覧ページにリダイレクトされることを確認するテスト
    @Test
    void 正しい認証情報ならログイン成功して一覧へ遷移() throws Exception {
        mockMvc.perform(formLogin("/login")
                .user("testuser")
                .password("password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tasks"));
    }
//誤った認証情報でログインした場合、ログインページにリダイレクトされることを確認するテスト
    @Test
    void 認証失敗時はログイン画面へ戻る() throws Exception {
        mockMvc.perform(formLogin("/login")
                .user("testuser")
                .password("wrongpassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"));
    }
//ログアウト後にログインページにリダイレクトされることを確認するテスト
    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    void ログアウト後はログイン画面へ戻る() throws Exception {
        mockMvc.perform(post("/logout").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?logout"));
    }
}