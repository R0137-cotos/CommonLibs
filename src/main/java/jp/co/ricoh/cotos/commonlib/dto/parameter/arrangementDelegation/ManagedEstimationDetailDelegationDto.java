package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangementDelegation;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ManagedEstimationDetailDelegationDto {

	/**
	 * 見積明細管理ID
	 */
	@NotNull
	@Min(0)
	@ApiModelProperty(value = "見積明細管理ID", required = true, position = 1, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * レートプランチャージ情報
	 */
	@Valid
	@NotNull
	@ApiModelProperty(value = "レートプランチャージ情報", required = true, position = 2)
	private RatePlanChargeInfoDto ratePlanChargeInfo;

}
