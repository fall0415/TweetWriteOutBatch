# TweetWriteOutBatch
TwitterAPIを使い、application.ymlに設定されているユーザのツイートのデータを取得する。

### 動作環境
- Java 11

### バッチを使用する上で各個人で設定してもらいたいもの
- src/main/resources/credential.json→TwiiterAPIにリクエストを送る時に必要になるBearerTokenを設定
- src/main/resources/application.ymlの「param」配下→ツイートを取得するAPIのパラメータを定義(詳しくは公式ドキュメントを参照)
  - https://developer.twitter.com/en/docs/twitter-api/v1/tweets/timelines/api-reference/get-statuses-user_timeline

### バッチのoutput
- tweetData/tweetData_yyyy-MM-dd.json→ワンライナーのツイートデータがあるjson
- 出力されたjsonはpythonモジュールに加工させるか当該バッチで加工するかはこれから実装する

### リポジトリ管理者について
- twiiter:@main_fall0415まで
