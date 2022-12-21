package jp.co.ricoh.cotos.commonlib.dto.result;

import lombok.Data;

/**
 * 契約承認前確認API返却値格納用Dtoクラス
 */
@Data
public class BeforeApprovalWarning {

	/**
	 * アラートID
	 */
	private String AlertID;

	/**
	 * アラートメッセージ
	 */
	private String AlertMessage;
}
