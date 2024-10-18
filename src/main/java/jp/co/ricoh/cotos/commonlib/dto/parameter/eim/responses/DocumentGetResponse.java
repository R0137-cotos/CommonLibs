package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses;

import lombok.Data;

/**
 * 文書取得のレスポンスボディ
 */

@Data
public class DocumentGetResponse {

	/**
	 * document
	 */
	private DocumentGetResponseDocument document;

}
