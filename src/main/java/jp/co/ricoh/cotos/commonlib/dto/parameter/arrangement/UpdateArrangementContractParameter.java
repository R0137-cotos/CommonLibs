package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangement;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractDto;
import lombok.Data;

/**
 * 手配契約情報更新のためのパラメータクラスを表します。
 */
@Data
public class UpdateArrangementContractParameter {

	@Valid
	@Schema(description = "手配", required = true)
	private ArrangementDto arrangementDto;

	@Valid
	@Schema(description = "契約", required = true)
	private ContractDto contractDto;
}
