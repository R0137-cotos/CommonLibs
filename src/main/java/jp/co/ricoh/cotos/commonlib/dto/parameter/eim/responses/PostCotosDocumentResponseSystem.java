package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses;

import lombok.Data;

/**
 * 文書登録（COTOS申込書）のsystemレスポンスボディ
 */

@Data
public class PostCotosDocumentResponseSystem {

	/**
	 * documentId
	 */
	private String documentId;
	
	/**
	 * documentKey
	 */
	private String documentKey;
}
