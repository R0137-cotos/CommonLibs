package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationApprovalRoute;
import lombok.Data;

/**
 * 見積承認者特定用APIの実施結果用パラメーター
 */

@Data
public class ApprovalCandidateSearchResult {
	
	/**
	 * 承認ルートマスタ
	 */
	@Schema(description = "承認ルートマスタ情報", requiredMode = Schema.RequiredMode.REQUIRED)
	private ApprovalRouteMasterDto approvalRouteMaster;

	/**
	 * 条件式判定結果
	 */
	@Schema(description = "条件式判定結果", requiredMode = Schema.RequiredMode.REQUIRED)
	private RouteFormulaResult routeFormulaResult;

	/**
	 * 見積承認ルート
	 */
	@Schema(description = "見積承認ルート情報", requiredMode = Schema.RequiredMode.REQUIRED)
	private EstimationApprovalRoute estimationApprovalRoute;
}
