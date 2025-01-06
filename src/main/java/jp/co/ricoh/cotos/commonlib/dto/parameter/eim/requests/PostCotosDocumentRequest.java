package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.requests;

import lombok.Data;

/**
 * 文書登録（COTOS申込書）のリクエストボディ
 */

@Data
public class PostCotosDocumentRequest {

	/**
	 * system
	 */
	private PostCotosDocumentRequestSystem system;

	/**
	 * properties
	 */
	private PostCotosDocumentRequestProperties properties;
}
