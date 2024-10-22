package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses;

import lombok.Data;

/**
 * 文書更新（論理削除）のsystemレスポンス
 */

@Data
public class DocumentDeleteResponseSystem {

	/**
	 * documentId
	 */
	private String documentId;

	/**
	 * documentKey
	 */
	private String documentKey;

}
