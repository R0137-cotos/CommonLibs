package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "違約金明細(見積用)を表すEntity", required = true, position = 1)
	private PenaltyDetailEstimation penaltyDetailEstimation;
}