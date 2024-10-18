package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.responses;

import lombok.Data;

/**
 * 文書更新(論理削除)のレスポンスボディ
 */

@Data
public class DocumentDeleteResponse {

	/**
	 * system
	 */
	private DocumentDeleteResponseSystem system;

}
