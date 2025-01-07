package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.requests;

import lombok.Data;

/**
 * 文書更新（論理削除）のリクエスト
 */

@Data
public class DocumentDeleteRequest {

	/**
	 * system
	 */
	private DocumentDeleteRequestSystem system;

	/**
	 * properties
	 */
	private DocumentDeleteRequestProperties properties;
}
