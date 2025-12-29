# Contributing

## 前提
- Java 21 以上
- PostgreSQL
- Git

## セットアップ
[クイックスタート](./README.md#クイックスタート)

### PostgresQL（ローカル）の場合の詳細手順
1. リポジトリをクローン
2. 以下のコマンドを実行（環境変数ファイルを作成）
```shell
$ cp .env.example .env
```
3. PostgreSQL の準備
   - `psql --version` でローカルにインストールされているか確認
   - pgAdmin.appなどを使ってDBを新規作成し、名前を `kintaisan`に設定
4. 設定ファイルの更新
   - `.env`内の`DB_USERNAME`と`DB_PASSWORD`の値を設定
5. 起動確認
   - [KintaisanApplication.java](./src/main/java/com/kyosaka/kintaisan/KintaisanApplication.java) のメイン関数を実行し、エラーがでなければOK

## 開発フロー
1. 開発タスクを確認
2. ブランチを切って作業
3. 変更をコミット
4. プルリクエストを作成
5. レビュー後にmainブランチにマージ

## ブランチルール
- [git branch rule](./docs/branch.md)

## コミットメッセージ
- 変更点を一文で短く要約する
- 好みで先頭にプレフィックス絵文字を付ける（例: `✨ add attendance export`）
- 必要ならテンプレート設定を行う（手順は [commit prefix](./docs/commit.md)）

## プルリクエスト
1. タイトルは日本語で明確に
2. [テンプレート](./.github/pull_request_template.md)に基づき、変更内容の要約をする。可能であればエビデンス（スクショ・gif）をつける
3. レビュアーをアサインし、レビューを待つ
4. OKが出たら`squash and merge`でマージする（コミット履歴が見やすくなる）

## テスト（編集中）
- `./mvnw test`