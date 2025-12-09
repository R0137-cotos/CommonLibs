package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangementDelegation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RatePlanChargeInfoDto {

	/**
	 * プロダクトID
	 */
	@Size(max = 255)
	@Schema(description = "プロダクトID", required = false, allowableValues = "range[0,255]")
	private String productId;

	/**
	 * 料金プランID
	 */
	@Size(max = 255)
	@Schema(description = "料金プランID", required = false, allowableValues = "range[0,255]")
	private String productRatePlanId;

	/**
	 * 料金プランチャージID
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "料金プランチャージID", required = true, allowableValues = "range[0,255]")
	private String productRatePlanChargeId;

	/**
	 * レートプランチャージID
	 */
	@Size(max = 255)
	@Schema(description = "レートプランチャージID", required = false, allowableValues = "range[0,255]")
	private String ratePlanChargeId;

}
