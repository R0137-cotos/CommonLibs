package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation;

import java.math.BigDecimal;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class EstimationDetailRegisterParameter {

	/**
	 * ステータス
	 */
	@NotNull
	@Size(max = 255)
	@Parameter(description = "ステータス", schema = @Schema(allowableValues = "range[0,255]"))
	private String status;

	/**
	 * 品種コード
	 */
	@NotNull
	@Size(max = 255)
	@Parameter(description = "品種コード", schema = @Schema(allowableValues = "range[0,255]"))
	private String ricohItemCode;

	/**
	 * 品種名
	 */
	@Size(max = 255)
	@Parameter(description = "品種名", schema = @Schema(allowableValues = "range[0,255]"))
	private String ricohItemName;

	/**
	 * 変更前数量
	 */
	@Max(99999)
	@Min(-99999)
	@Schema(description = "変更前数量", allowableValues = "range[-99999,99999]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer beforeQuantity;

	/**
	 * 数量
	 */
	@Min(-99999)
	@Max(99999)
	@Parameter(description = "数量", schema = @Schema(allowableValues = "range[-99999,99999]"))
	private int quantity;

	/**
	 * 見積単価
	 */
	@Parameter(description = "見積単価", schema = @Schema(allowableValues = "range[0.00,9999999999999999999.99]"))
	private BigDecimal unitPrice;

	/**
	 * 見積金額
	 */
	@NotNull
	@Digits(integer = 19, fraction = 2)
	@Parameter(description = "見積金額", schema = @Schema(allowableValues = "range[-9999999999999999999.99,9999999999999999999.99]"))
	private BigDecimal amountSummary;

	/**
	 * R原価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Parameter(description = "R原価", schema = @Schema(allowableValues = "range[0.00,9999999999999999999.99]"))
	private BigDecimal rCost;

	/**
	 * ＲＪ仕入価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Parameter(description = "ＲＪ仕入価格", schema = @Schema(allowableValues = "range[0.00,9999999999999999999.99]"))
	private BigDecimal rjPurchasePrice;

	/**
	 * ＲＪ仕切価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Parameter(description = "ＲＪ仕切価格", schema = @Schema(allowableValues = "range[0.00,9999999999999999999.99]"))
	private BigDecimal rjDividingPrice;

	/**
	 * 母店売価(接点店仕切)
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Parameter(description = "母店売価(接点店仕切)", schema = @Schema(allowableValues = "range[0.00,9999999999999999999.99]"))
	private BigDecimal motherStorePrice;

	/**
	 * 契約期間
	 */
	@Parameter(description = "契約期間")
	private String contractSpan;

	/**
	 * 拡張項目
	 */
	@Parameter(description = "拡張項目")
	@Lob
	private String extendsParameter;

	/**
	 * 契約数
	 */
	@Max(99999)
	@Min(-99999)
	@Schema(description = "契約数", allowableValues = "range[-99999,99999]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer contractAmount;

	/**
	 * 価格改定前リコー品種コード
	 */
	@Size(max = 255)
	@Parameter(description = "価格改定前リコー品種コード", schema = @Schema(allowableValues = "range[0,255]"))
	private String bfPriceRevisionItemCode;
}
