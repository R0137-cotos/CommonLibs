package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses;

import lombok.Data;

/**
 * アプリケーション連携のレスポンスボディクラス
 */

@Data
public class ApiAuthResponse {

	/**
	 * accessToken
	 */
	private String accessToken;
}
