package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 契約を検索するためのキー項目クラスを表します。
 */

@Data
public class ApprovalSearchParameterContract {

	/**
	 * 契約ID
	 */
	@Parameter(description = "契約ID")
	private Long contractId;

	/**
	 * MoM社員ID（承認依頼者）
	 */
	@Parameter(description = "社員ID")
	private String employeeId;

	/**
	 * 承認ルート登録フラグ
	 */
	@Parameter(description = "承認ルート登録フラグ", schema = @Schema(allowableValues = "true, false"))
	private boolean approvalRouteRegisterFlg = false;
}
