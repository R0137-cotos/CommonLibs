package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWorkApprovalRoute;
import lombok.Data;

/**
 * 手配承認者特定用APIの実施結果用パラメーター
 */
@Data
public class ApprovalSearchResultArrangement {

	/**
	 * 条件式判定結果
	 */
	@Schema(description = "条件式判定結果", requiredMode = Schema.RequiredMode.REQUIRED)
	private RouteFormulaResult routeFormulaResult;

	/**
	 * 手配承認ルート
	 */
	@Schema(description = "手配承認ルート情報", requiredMode = Schema.RequiredMode.REQUIRED)
	private ArrangementWorkApprovalRoute arrangementWorkApprovalRoute;
}