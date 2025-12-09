package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

/**
 * 見積を検索するためのキー項目クラスを表します。
 */

@Data
public class ApprovalSearchParameter {

	/**
	 * 見積ID
	 */
	@Parameter(description = "見積ID", required = true)
	private Long estimationId;

	/**
	 * MoM社員ID（承認依頼者）
	 */
	@Parameter(description = "社員ID", required = true)
	private String employeeId;

	/**
	 * 承認ルート登録フラグ
	 */
	@Parameter(description = "承認ルート登録フラグ", required = true, allowableValues = "true, false")
	private boolean approvalRouteRegisterFlg = false;
}
