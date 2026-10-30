# aws-study-2026-app
AWS勉強会2026作成アプリ

## パッケージ構成
基本パッケージは `jp.co.sfrontier.app` とする。

### 機能別のパッケージ
以下の名前は、基本パッケージからの相対名とする。<br>
必要に応じて、パッケージの追加・削除を行う。

| パッケージ | 役割 |
|---|---|
| common | 共通処理 |
| common.exception | 複数機能で使用する例外 |
| auth | ログイン・ログアウト、認証連携 |
| user | ユーザーの登録・参照・編集・管理 |
| home | ホーム画面 |
| game.janken | じゃんけん |
| game.lookoverthere | あっちむいてほい |
| game.highlow | HighLow |
| history | 対戦履歴の記録・参照、成績の集計 |

## ブランチ運用ルール
### ブランチの役割
| ブランチ | 用途 | マージ先 |
|---|---|---|
| main | リリースする状態を管理する | - |
| develop | 開発した機能を統合する | main |
| feature/* | 機能追加・修正などの作業を行う | develop |

通常の作業は、最新のdevelopから作業用ブランチを作成して進める。<br>
main・developへの直接プッシュは原則行わない。
