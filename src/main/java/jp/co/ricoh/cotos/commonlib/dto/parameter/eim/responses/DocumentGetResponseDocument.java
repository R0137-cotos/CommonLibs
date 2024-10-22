package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses;

import lombok.Data;

/**
 * 文書取得のpropertiesレスポンスボディ
 */

@Data
public class DocumentGetResponseDocument {

	/**
	 * properties
	 */
	private DocumentGetResponseProperties properties;
}
