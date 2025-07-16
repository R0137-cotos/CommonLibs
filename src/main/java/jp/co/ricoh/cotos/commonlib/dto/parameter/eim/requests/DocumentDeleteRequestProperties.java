package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.requests;

import lombok.Data;

/**
 * 文書更新（論理削除）のpropertiesリクエスト
 */

@Data
public class DocumentDeleteRequestProperties {

	/**
	 * 削除フラグ
	 */
	private String deleteFlag;

}
