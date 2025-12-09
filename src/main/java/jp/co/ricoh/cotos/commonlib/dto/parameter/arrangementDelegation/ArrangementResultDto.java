package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangementDelegation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 
 * 手配結果登録APIリクエストパラメータDTO
 *
 */

@Data
public class ArrangementResultDto implements IArrangementResult {

	/**
	 * 手配結果情報
	 */
	@Valid
	@NotNull
	@Schema(description = "手配結果情報", required = true)
	private ArrangementResultInfoDto arrangementResultInfo;

	/**
	 * 契約情報
	 */
	@Valid
	@NotNull
	@Schema(description = "契約情報", required = true)
	private ContractInfoDto contructInfo;

}
