package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.contract.PenaltyDetailContract;
import lombok.Data;

/**
 * 契約違約金計算APIの実施結果用パラメーター
 */

@Data
public class ContractPenaltyCalculationResult {

	/**
	 * 違約金明細(契約用)を表すEntity
	 */
	@ApiModelProperty(value = "違約金明細(契約用)を表すEntity", required = true, position = 1)
	private PenaltyDetailContract penaltyDetailContract;

	/**
	 * 違約金発生解約最終日
	 */
	@ApiModelProperty(value = "違約金発生解約最終日", required = false, position = 2)
	@Temporal(TemporalType.DATE)
	private Date penaltyIncurredLastDay;
}