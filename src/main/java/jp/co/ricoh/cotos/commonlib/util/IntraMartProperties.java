package jp.co.ricoh.cotos.commonlib.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Intra-mart連携設定
 *
 */
@Component
@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cotos.intramart")
public class IntraMartProperties {

	/**
	 * APIURL（開発環境）
	 */
	private String apiUrl = "https://sld.imp.ricoh.co.jp/imsl/logic/api/sf_nim006_fd904";

}
