package jp.co.ricoh.cotos.commonlib.util;

import lombok.Data;

/**
 * トレンドマイクロ連携設定のベースクラス
 *
 */
@Data
public class LMPIPropertiesBase {

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
	 * 更新ユーザ取得最大数
	 */
	private int updateCustomerInfoGetLimit = 25;

	/**
	 * URLの接頭辞を返す。
	 * @return　apiUrl + "/" + version + "/"
	 */
	public String getUrlPrefix() {
		return apiUrl + "/" + version;
	}

}
