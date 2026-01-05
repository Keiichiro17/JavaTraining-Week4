概要
Spring Boot3を用いて作成したWebアプリケーションの作成とブラウザからアクセス可能なHello APIを実装し、Spring Bootアプリケーションの起動確認及び、基本的なコントローラの動作の確認
。

開発環境　VSCODE

セットアップ手順と動作確認手順
git bashで./gradlew bootRun、gradlew clean bootRunで起動。

エラーが出た場合の対処
アプリケーション起動時に main クラスが複数存在するエラーが発生した時起動クラスを1つに統一し、不要な Application クラスを削除して解決。

