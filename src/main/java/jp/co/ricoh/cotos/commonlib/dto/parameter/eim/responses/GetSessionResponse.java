package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses;

import lombok.Data;

/**
 * 	セッション取得のレスポンスボディクラス
 */

@Data
public class GetSessionResponse {

	/**
	 * csrfToken
	 */
	private String csrfToken;
}
