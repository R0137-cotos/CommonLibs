package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractApprovalRoute;
import lombok.Data;

/**
 * 契約承認者特定用APIの実施結果用パラメーター
 */

@Data
public class ApprovalSearchResultContract {

	/**
	 * 条件式判定結果
	 */
	@Schema(description = "条件式判定結果", required = true)
	private RouteFormulaResult routeFormulaResult;

	/**
	 * 契約承認ルート
	 */
	@Schema(description = "契約承認ルート情報", required = true)
	private ContractApprovalRoute contractApprovalRoute;
}
