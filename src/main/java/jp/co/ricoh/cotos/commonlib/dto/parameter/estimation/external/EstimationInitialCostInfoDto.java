package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation.external;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EstimationInitialCostInfoDto {

	/**
	 * 初期費 品種コード
	 */
	@NotNull
	@Size(max = 25)
	@Schema(description = "初期費 品種コード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,25]")
	private String initialProductCd;

	/**
	 * 初期費 標準価格
	 */
	@NotNull
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "初期費 標準価格", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal initialUnitPrice;

	/**
	 * 初期費 見積り単価
	 */
	@NotNull
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "初期費 見積り単価", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal initialEstimatedUnitPrice;

	/**
	 * 初期費 数量
	 */
	@NotNull
	@Min(-99999)
	@Max(99999)
	@Schema(description = "初期費 数量", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[-99999,99999]")
	private Integer initialAmt;

	/**
	 * 初期費 見積り金額
	 */
	@NotNull
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "初期費 見積り金額", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal initialEstimatedPrice;
}
