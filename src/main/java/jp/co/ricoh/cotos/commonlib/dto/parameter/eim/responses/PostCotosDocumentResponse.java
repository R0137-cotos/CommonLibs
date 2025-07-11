package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses;

import lombok.Data;

/**
 * 文書登録（COTOS申込書）のレスポンスボディ
 */

@Data
public class PostCotosDocumentResponse {

	/**
	 * system
	 */
	private PostCotosDocumentResponseSystem system;
}
