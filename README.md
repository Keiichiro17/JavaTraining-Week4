概要

SpringBootとThymeleafを用いたタスク管理アプリにSpring Securityを用いたログイン認証機能を追加。

ログイン情報

ユーザー名:　testuser
パスワード:　password

動作確認手順

1.Gitbashでgradlew bootRunを行いアプリケーションを起動。
2.http://localhost:8080/loginにアクセスする
3.testuser/passwordでログインする。
4.自動でtasksページに移動
5.ログアウト後、ログイン画面に戻る。

