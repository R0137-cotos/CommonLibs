package jp.co.ricoh.cotos.commonlib.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Intra-mart用設定
 */
@Component
@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cotos.intramart")
public class IntramartProperties {
	/**
	 * 接続先基本URL
	 */
	String url;

	/**
	 * BASIC認証用ユーザ
	 */
	String user;

	/**
	 * BASIC認証用パスワード
	 */
	String password;
}
