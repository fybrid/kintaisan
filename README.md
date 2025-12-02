# 勤怠さん

## 使用技術一覧
- HTML
- CSS
- JavaScript
- Java
  JDK21以上（VScodeの拡張機能を使用するなら）
- Springboot
- PostgreSQL (DB)

## クイックスタート

### DBの接続（ローカルPosrgreSQL）

1. PostgreSQLがローカルにインストールされているか確認

```shell
$ psql --version
```
2. pgAdmin.appを開く

3. 左のアイコン右クリックなどで、新しくDBを作成し、DB名を「kintaisan」にする

4. [設定ファイル](./src/main/resources/application.properties)のパスワードの値をpostgresユーザーのパスワードに変更する

5. [メイン関数](./src/main/java/com/kyosaka/kintaisan/KintaisanApplication.java)をRunさせてエラーが出なければOK

## ドキュメント
- [git branch rule](./docs/branch.md)