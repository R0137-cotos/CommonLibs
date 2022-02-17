package jp.co.ricoh.cotos.commonlib.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * トレンドマイクロ連携設定(SMPI)
 * @author z00se03039
 *
 */
@Component
@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cotos.smpi")
public class SMPIProperties {

	/**
	 * APIURL
	 */
	private String apiUrl = "https://cspi-stg.trendmicro.com/SMPI";

	/**
	 * APIバージョン
	 */
	private String version = "v2.2";

	/**
	 * サービス
	 */
	private String service = "/service";

	/**
	 * アクセストークン
	 */
	private String accessToken = "74903e85-ee87-4712-97e1-5e30292e435c";

	/**
	 * シークレットキー
	 */
	private String secretKey = "zGPxd/SVAeevhxleN3SBPnvqM2SddrVBMNDW2JaTQs4=";

	/**
	 * URLの接頭辞を返す。
	 * @return apiUrl + "/" + version + service
	 */
	public String getUrlPrefix() {
		return apiUrl + "/" + version + service;
	}

}
