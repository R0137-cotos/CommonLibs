package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import lombok.Data;

/**
 * SACMサービスアダプタ情報更新リクエスト
 */
@Data
public class SACMUpdateServiceAdapterInfoRequestParameter {

	/**
	 * SAラベル
	 */
	private String name;

	/**
	 * メモ
	 */
	private String description;

	/**
	 * Distribution ID
	 */
	private String distributionId;

	/**
	 * RSが提案する接続モード（オプション）
	 */
	private String preferredPushMethod;

}
