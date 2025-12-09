package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangementDelegation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ContractDetailDelegationDto {

	/**
	 * 契約明細ID
	 */
	@NotNull
	@Min(0)
	@Schema(description = "契約明細ID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * 品種(契約用)
	 */
	@Valid
	@NotNull
	@Schema(description = "品種(契約用)", required = true)
	private ItemContractDelegationDto itemContract;

	/**
	 * レートプランチャージ情報
	 */
	@Valid
	@NotNull
	@Schema(description = "レートプランチャージ情報", required = true)
	private RatePlanChargeInfoDto ratePlanChargeInfo;

}
