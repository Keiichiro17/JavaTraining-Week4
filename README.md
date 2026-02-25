・目的・機能概要

SpringBootとThymeleafを用いて開発したタスク管理用のWebアプリケーションを作成。

タスクの登録、編集、削除、完了管理を行い、バリデーションエラーやフラッシュメッセージなどが表示される。


・起動手順

1.Gitbashにてcd/~でファイルに入る

2../gradlew bootRunで起動。


・画面・URL一覧

一覧表示　/tasks　タスク一覧を表示
新規作成　/tasks/new　タスク登録画面
登録　/tasks　POSTで登録
編集　/tasks/{id}/edit　タスク編集画面
更新　/tasks/{id}　POSTで更新
削除　/tasks/{id}/delete　タスク削除　
完了切替　/tasks/{id}/toggle　完了状態切替


・バリデーションと例外処理

バリデーションは必須入力（＠NotBlank）と最大50文字（＠Size)に設定

エラー発生時はフィールドにエラーメッセージを表示

例外処理は存在しないIDにアクセスした場合、TaskNotFoundExceptionにより、404エラーページに移行するよう設定。


・任意機能

一覧画面の「完了切替」ボタンを押すことで、タスクの完了・未完了を切りかえが可能。