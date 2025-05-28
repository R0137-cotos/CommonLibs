package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangement;

import jakarta.validation.Valid;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractDto;
import lombok.Data;

/**
 * 手配契約情報更新のためのパラメータクラスを表します。
 */
@Data
public class UpdateArrangementContractParameter {

	@Valid
	@ApiModelProperty(value = "手配", required = true)
	private ArrangementDto arrangementDto;

	@Valid
	@ApiModelProperty(value = "契約", required = true)
	private ContractDto contractDto;
}
