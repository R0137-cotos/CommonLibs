package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.requests;

import lombok.Data;

/**
 * 文書登録（COTOS申込書）のsystemリクエストボディ
 */

@Data
public class PostCotosDocumentRequestSystem {

	/**
	 * appId
	 */
	private String appId;

	/**
	 * modelId
	 */
	private String modelId;
}
