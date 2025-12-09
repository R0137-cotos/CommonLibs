package jp.co.ricoh.cotos.commonlib.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cotos.externallog.response")
public class ExternalLogResponseProperties {

	/**
	 * APIレスポンスログ出力有無
	 */
	private boolean outputLog;

	/**
	 * APIレスポンスログ出力サイズ上限
	 */
	private Integer outputLogSizeLimit;
}
