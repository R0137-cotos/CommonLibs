package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangement;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWork;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract;
import lombok.Data;

/**
 * ルート条件式を使用した検索のためのパラメータクラスを表します。
 */
@Data
public class ApprovalSearchArrangementWorkContractParameter {

	@Valid
	@Schema(description = "手配業務", required = true)
	private ArrangementWork arrangementWork;

	@Valid
	@Schema(description = "契約", required = true)
	private Contract contract;
}
