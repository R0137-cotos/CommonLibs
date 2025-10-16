package jp.co.ricoh.cotos.commonlib.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cotos.microsoft")
public class MicrosoftProperties {
	/**
	 * Microsoft 認証APIのベースURL
	 */
	String authorizationBaseUrl;

	/**
	 * Microsoft 認証APIのURL
	 */
	String authorizationUrl;

	/**
	 * Microsoft 顧客・サブスクリプション取得APIのベースURL
	 */
	String baseUrl;

	/**
	 * Microsoft 顧客取得APIのURL
	 */
	String customerUrl;

	/**
	 * Microsoft サブスクリプション取得APIのURL
	 */
	String subscriptionUrl;

	/**
	 * Microsoft アプリID
	 */
	String applicationId;

	/**
	 * Microsoft アカウントID
	 */
	String accountId;

	/**
	 * Microsoft キーID
	 */
	String keyId;

	/**
	 * Microsoft リフレッシュトークン
	 * 暫定対応として認証情報取得APIで利用する
	 * ⇒ トークン外出し対応によりrefreshTokenTextFilePathのtxtファイルからリフレッシュトークン取得に修正したため未使用
	 */
	String refreshToken;

	/**
	 * Microsoft リフレッシュトークン外出しtxtファイルパス
	 * 認証情報取得APIで利用する
	 */
	String refreshTokenTextFilePath;
}
