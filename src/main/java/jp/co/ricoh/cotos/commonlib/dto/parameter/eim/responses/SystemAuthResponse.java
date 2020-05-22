package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses;

import lombok.Data;

/**
 * システム認証のレスポンスボディクラス
 */

@Data
public class SystemAuthResponse {

	/**
	 * URL
	 */
	private String applicationId;

	/**
	 * HEADERS
	 */
	private String applicationKey;

	/**
	 * siteId
	 */
	private String siteId;
}
