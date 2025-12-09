package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "違約金明細(契約用)を表すEntity")
	private PenaltyDetailContract penaltyDetailContract;

	/**
	 * 違約金発生解約最終日
	 */
	@Schema(description = "違約金発生解約最終日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date penaltyIncurredLastDay;
}