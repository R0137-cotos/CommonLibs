以下、やったことを確認しています。
 - [ ] Pull Request名にはチケット番号を入れてあります。
 - [ ] マイルストーンを設定しました。
 - [ ] build.gradleのpom.versionをあげました。
 - [ ] README.MDを追記しました。
 - [ ] 自動テストは追記され、完璧です。
 - [ ] ソースコードフォーマットはEclipseでバッチリです。
 - [ ] [プルリクレビュー依頼前のチェック観点](https://ex-redmine-1.cotos.ricoh.co.jp/projects/cotos_root/wiki/%E3%83%97%E3%83%AB%E3%83%AA%E3%82%AF%E3%83%AC%E3%83%93%E3%83%A5%E3%83%BC%E4%BE%9D%E9%A0%BC%E5%89%8D%E3%81%AE%E3%83%81%E3%82%A7%E3%83%83%E3%82%AF%E8%A6%B3%E7%82%B9)、および[コーディングルール](https://ex-redmine-1.cotos.ricoh.co.jp/projects/cotos_root/wiki/%E3%82%B3%E3%83%BC%E3%83%87%E3%82%A3%E3%83%B3%E3%82%B0%E3%83%AB%E3%83%BC%E3%83%AB)を確認の上、実装しました。

以下は該当する場合のみ確認しています。
 - [ ] Messages.propertiesにエラーIDを追加する際、[エラーID重複チェックジョブ](http://ci.cotos.ricoh.co.jp/view/Tools/job/CheckCommonLibsErrorMsgId/)にて重複が無いことを確認しました。
 - [ ] enum追加時にConverterと@Descriptionを追加しています。
 - [ ] N+1問題は発生させていません。
 - [ ] Date型の比較で時分秒の考慮が不要な場合は、切り捨てた上で比較しています。
 - [ ] テーブルとマッピングするEntityまたはDTOのDate型項目に@Temporalを付与しています。
 - [ ] 新規コードの場合、既存コードの部分的な修正以外の場合は、日付/日時の型としてLocalDate/LocalDateTimeを使用し、計算/比較/変換処理は[サンプルコード](https://mygithub.ritscm.xyz/cotos/SampleCode/blob/master/src/main/java/dateAndTimeSample/Main.java)を確認して実装しています。
