package jp.co.ricoh.cotos.commonlib.dto.parameter.eim.requests;

import lombok.Data;

/**
 * 文書更新（論理削除）のsystemリクエスト
 */

@Data
public class DocumentDeleteRequestSystem {

	/**
	 * アプリケーションId
	 */
	private String appId;

	/**
	 * modelId
	 */
	private String modelId;
}
