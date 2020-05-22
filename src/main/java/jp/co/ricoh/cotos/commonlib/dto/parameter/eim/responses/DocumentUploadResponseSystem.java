package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses;

import lombok.Data;

/**
 * 文書アップロードのsystemレスポンスボディ
 */

@Data
public class DocumentUploadResponseSystem {

	/**
	 * documentId
	 */
	private String documentId;
	
	/**
	 * documentKey
	 */
	private String documentKey;
}
