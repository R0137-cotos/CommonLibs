package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.requests;

import lombok.Data;

/**
 * 文書登録（COTOS申込書）のpropertiesリクエストボディ
 */

@Data
public class PostCotosDocumentRequestProperties {

	/**
	 * システム名
	 */
	private String systemName;

	/**
	 * タイトル
	 */
	private String title;

	/**
	 * 添付ファイルID
	 */
	private String[] documentUniqueID;
}
