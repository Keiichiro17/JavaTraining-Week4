目的と機能概要

SpringBootとThymeleafを用いて、タスク管理アプリを作成。
画面からタスクの作成、一覧表示、編集、削除、完了状態の切り替え、保存の機能を追加。


アプリの起動手順

Git Bashでcd~でフォルダーに入り./gradlew bootRun


画面遷移

一覧　GET/tasks　タスク一覧を表示

新規作成フォーム　GET/tasks/new　タスク作成フォームを表示

新規作成　POST/tasks　タスクを登録

編集フォーム　GET/tasks/{id}/edit　タスク編集フォームを表示

更新　POST/tasks/{id}　タスクを更新

削除　POST/tasks/{id}/delete　タスクを削除

完了切替　POST/tasks/{id}/toggle　完了/未完了を切り替え


・バリデーション

タスクタイトルを必須入力（最大50文字）にし、空欄で入力したときに発生するエラー時は画面にエラーメッセージを表示。

・例外処理

存在しない、不正なIDにアクセスした場合、Http404としてtemplates/error/404.htmlでエラーメッセージを表示し、一覧に戻れる機能を追加。


