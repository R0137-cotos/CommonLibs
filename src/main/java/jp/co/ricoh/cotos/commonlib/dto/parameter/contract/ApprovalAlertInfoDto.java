package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import lombok.Data;

/**
 * 承認アラートAPI返却値格納用Dtoクラス
 */
@Data
public class ApprovalAlertInfoDto {

	/**
	 * アラートメッセージ
	 */
	private String AlertMessage;

}
