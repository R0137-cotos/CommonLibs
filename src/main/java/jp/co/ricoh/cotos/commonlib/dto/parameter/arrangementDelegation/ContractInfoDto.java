package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangementDelegation;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ContractInfoDto {

	/**
	 * 契約番号
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "契約番号", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "契約番号枝番", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String contractBranchNumber;

	/**
	 * 契約明細情報リスト
	 */
	@Valid
	@Schema(description = "契約明細情報リスト", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ContractDetailDelegationDto> contractDetailList;

	/**
	 * 商品(契約用)リスト
	 */
	@Valid
	@Schema(description = "商品(契約用)リスト")
	private List<ProductContractDelegationDto> productContractList;

}
