package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.estimation.PenaltyDetailEstimation;
import lombok.Data;

/**
 * 見積違約金計算APIの実施結果用パラメーター
 */

@Data
public class EstimationPenaltyCalculationResult {

	/**
	 * 違約金明細(見積用)を表すEntity
	 */
	@Schema(description = "違約金明細(見積用)を表すEntity", required = true)
	private PenaltyDetailEstimation penaltyDetailEstimation;
}