package jp.co.ricoh.cotos.commonlib.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cotos.sacm")
public class SACMProperties {

	/**
	 * アクセスキー
	 */
	private String accessKey;

	/**
	 * アクセスキーシークレット
	 */
	private String AccessKeySecret;

	/**
	 * APIURL
	 */
	private String apiUrl;

}
