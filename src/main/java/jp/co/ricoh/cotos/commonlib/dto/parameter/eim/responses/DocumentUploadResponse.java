package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses;

import lombok.Data;

/**
 * 文書アップロードのレスポンスボディ
 */

@Data
public class DocumentUploadResponse {

	/**
	 * system
	 */
	private DocumentUploadResponseSystem system;
}
