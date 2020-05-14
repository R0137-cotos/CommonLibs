package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.requests;

import lombok.Data;

/**
 * アプリケーション認証のリクエストボディ
 */

@Data
public class ApiAuthRequest {

	/**
	 * ログインユーザ名
	 */
	private String loginUserName;

	/**
	 * ログインPW
	 */
	private String loginPassword;
}
