・概要

ログイン機能付きのタスク管理アプリ。ユーザーはログイン後、タスクの一覧表示、登録、編集、削除、完了切替が使用可能。また、REST APIを通じたタスク操作にも対応。


・開発環境

JAVA:17
フレームワーク:Spring Boot
DB: H2 Database
IDE:VS Code



・セットアップ手順


gitbashで./gradlew bootRunを行う


・動作確認手順

http://localhost:8080/loginにログイン（ユーザー名:testuser　パスワード:password)

ログイン後以下の操作が可能

タスク一覧表示（/tasks)

新規作成(/tasks/new)

編集(/tasks/{id}edit)

削除

完了状態の切り替え


・API確認

タスク一覧取得　curl http://localhost:8080/api/tasks

タスク登録　curl -X POST http://localhost:8080/api/tasks \
-H "Content-Type: application/json" \
-d '{"title":"テストタスク","completed":false}'

タスク更新　curl -X PUT http://localhost:8080/api/tasks/1 \
-H "Content-Type: application/json" \
-d '{"title":"更新タスク","completed":true}'

タスク削除　curl -X DELETE http://localhost:8080/api/tasks/1


テキスト図

Browser
  ↓
Controller（画面 / API）
  ↓
Service
  ↓
Repository
  ↓
Database（H2）


パッケージ構成

com.example.taskapp
├─ config                
│   └─ SecurityConfig
├─ controller           
│   ├─ LoginController
│   ├─ TaskViewController
│   ├─ TaskRestController
│   └─ form
│       └─ TaskForm
├─ entity               
│   ├─ Task
│   └─ UserAccount
├─ repository         
│   ├─ TaskRepository
│   └─ UserAccountRepository
├─ service              
│   ├─ TaskService
│   └─ UserAccountService
├─ exception            
│   ├─ TaskNotFoundException
│   ├─ GlobalExceptionHandler
│   └─ ViewExceptionHandler
└─ TaskAppApplication   

src/main/resources
├─ templates            
│   ├─ layout.html
│   ├─ login.html
│   └─ tasks
│       ├─ index.html
│       └─ form.html
└─ application.yml    

src/test/java/com/example/taskapp
└─ TaskControllerTest   


・既知の制約、今後の改善点

複数のユーザー登録機能が未実装

生成AIに頼り切りだったため、自分で構築できる割合を増やす

まだ、APIやアプリケーション設計の理解が浅いため、繰り返し学習し、自分自身で適切な設計をできるようにしたい。



