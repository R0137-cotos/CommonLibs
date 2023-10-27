package jp.co.ricoh.cotos.commonlib.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cotos.rsi")
public class RsiProperties {
	/**
	 * RSI 契約管理APIのベースURL
	 */
	String contractUrl;

	/**
	 * RSI 契約管理APIのベースURL 日本DC接続用
	 */
	String contractUrlForJpdc;

	/**
	 * RSI 契約管理APIのユーザー認証用URL
	 */
	String authUrl;

	/**
	 * RSI 契約管理APIのユーザー認証用URL 日本DC接続用
	 */
	String authUrlForJpdc;

	/**
	 * RSI 基本ユーザー情報APIのベースURL
	 */
	String basicUserInfoUrl;

	/**
	 * RSI 基本ユーザー情報APIのベースURL 日本DC接続用
	 */
	String basicUserInfoUrlForJpdc;

	/**
	 * RSI 契約管理APIのユーザー認証用ユーザーID
	 */
	String userId;

	/**
	 * RSI 契約管理APIのユーザー認証用パスワード
	 */
	String userPassword;

	/**
	 * RSI 契約管理APIのユーザー認証用テナントID
	 */
	String userTenantId;

	/**
	 * RSI 契約管理APIのアプリケーション認証用ユーザーID
	 */
	String appUserId;

	/**
	 * RSI 契約管理APIのアプリケーション認証用パスワード
	 */
	String appUserPassword;
}
