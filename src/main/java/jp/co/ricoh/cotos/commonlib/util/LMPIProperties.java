package jp.co.ricoh.cotos.commonlib.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * トレンドマイクロ連携設定
 * @author z00se03039
 *
 */
@Component
@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "cotos.lmpi")
public class LMPIProperties {

	/**
	 * APIURL
	 */
	private String apiUrl = "https://cspi-stg.trendmicro.com/LMPI";

	/**
	 * APIバージョン
	 */
	private String version = "v2";

	/**
	 * アクセストークン
	 */
	private String accessToken = "74903e85-ee87-4712-97e1-5e30292e435c";

	/**
	 * シークレットキー
	 */
	private String secretKey = "zGPxd/SVAeevhxleN3SBPnvqM2SddrVBMNDW2JaTQs4=";

	/**
	 * リトライ回数
	 */
	private int retryNum = 3;

	/**
	 * リトライ待機時間（ミリ秒）
	 */
	private int retryWaitTime = 5000;

	/**
	 * URLの接頭辞を返す。
	 * @return　apiUrl + "/" + version + "/"
	 */
	public String getUrlPrefix() {
		return apiUrl + "/" + version;
	}

}
