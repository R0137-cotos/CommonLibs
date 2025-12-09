package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWorkApprovalRoute;
import lombok.Data;

/**
 * 手配承認者特定用APIの実施結果用パラメーター
 */
@Data
public class ApprovalCandidateSearchResultArrangement {

	/**
	 * 承認ルートマスタ
	 */
	@Schema(description = "承認ルートマスタ情報", required = true)
	private ApprovalRouteMasterDto approvalRouteMaster;

	/**
	 * 条件式判定結果
	 */
	@Schema(description = "条件式判定結果", required = true)
	private RouteFormulaResult routeFormulaResult;

	/**
	 * 手配承認ルート
	 */
	@Schema(description = "手配承認ルート情報", required = true)
	private ArrangementWorkApprovalRoute arrangementWorkApprovalRoute;
}