package jp.co.ricoh.cotos.commonlib.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cotos.eim")
public class EimConnectionProperties {
	/**
	 * EIMのホスト名
	 */
	String hostName;

	/**
	 * EIMのドメイン名
	 */
	String domainName;

	/**
	 * システムサイト認証のパス
	 */
	String systemAuthPath;

	/**
	 * アプリケーション認証のパス
	 */
	String apiAuthPath;

	/**
	 * アプリケーション認証のためのログインユーザー名
	 */
	String loginUserName;

	/**
	 * アプリケーション認証のためのログインパスワード
	 */
	String loginPassword;

	/**
	 * セッション取得のパス
	 */
	String getSessionPath;

	/**
	 * ファイルアップロードのパス
	 */
	String fileUploadPath;

	/**
	 * COTOS用のアプリケーションID
	 */
	String appId;

	/**
	 * 月額DB用のアプリケーションID
	 */
	String appIdMonthDB;

	/**
	 * アプリ文書登録のためのリソースパス
	 */
	String resourcesPath;

	/**
	 * アプリ文書登録のパス
	 */
	String documentsPath;

	/**
	 * ファイルダウンロードのパス
	 */
	String fileDownloadPath;

	/**
	 * モデルID
	 */
	String modelId;

	/**
	 * システム名
	 */
	String systemName;

	/**
	 * サイトID
	 */
	String xSiteId;

	/**
	 * アプリケーションID
	 */
	String xApplicationId;

	/**
	 * アプリケーションキー
	 */
	String xApplicationKey;
}
