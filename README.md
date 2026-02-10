概要
Spring BootとSpring Data JPAを用いてタスク管理APIをDB対応に改良する。
タスク管理APIを拡張し、H2 Databaseを利用して永続化、及び、CRUDの確認と例外ハンドリング(400、404)を表示させる。

環境構築手順

1.リポジトリをクローン

gitbashでgit clone https://github.com/Keiichiro17/JavaTraining-Week4.git
cd JavaTraining-Week4


2.提出用ブランチに切り替え

git switch JavaTrainingWeek5


3.アプリケーションの起動

./gradlew bootRun

設定ファイル

src/main/resources/application.yml

H2コンソールは以下からアクセス

http://localhost:8080/h2-console



実行確認手順

Powershellにて以下のコマンドを使用し、APIの動作を確認

curl http://localhost:8080/api/tasks

1.タスク作成

curl -Method POST `
  -Uri http://localhost:8080/api/tasks `
  -ContentType "application/json" `
  -Body '{"title":"Sample Task","completed":false}'

2.タスク取得

curl http://localhost:8080/api/tasks/1

3.タスク更新

curl -Method PUT `
  -Uri http://localhost:8080/api/tasks/1 `
  -ContentType "application/json" `
  -Body '{"title":"Updated Task","completed":true}'

4.タスク削除

curl -Method DELETE http://localhost:8080/api/tasks/1


例外ハンドリングの動作例

1.エラー400

入力

$body = @{ title = "" } | ConvertTo-Json

irm http://localhost:8080/api/tasks `
  -Method Post `
  -ContentType "application/json" `
  -Body $body

返答

StatusCode : 400
要求が不適切です

2.エラー404

$body = @{ title="X"; completed=$false } | ConvertTo-Json

irm http://localhost:8080/api/tasks/9999 `
  -Method Put `
  -ContentType "application/json" `
  -Body $body

返答

StatusCode : 404
見つかりません

