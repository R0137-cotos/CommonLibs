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
	@Parameter(description = "ステータス", allowableValues = "range[0,255]", required = true)
	private String status;

	/**
	 * 品種コード
	 */
	@NotNull
	@Size(max = 255)
	@Parameter(description = "品種コード", allowableValues = "range[0,255]", required = true)
	private String ricohItemCode;

	/**
	 * 品種名
	 */
	@Size(max = 255)
	@Parameter(description = "品種名", allowableValues = "range[0,255]", required = true)
	private String ricohItemName;

	/**
	 * 変更前数量
	 */
	@Max(99999)
	@Min(-99999)
	@Schema(description = "変更前数量", allowableValues = "range[-99999,99999]", required = false)
	private Integer beforeQuantity;

	/**
	 * 数量
	 */
	@Min(-99999)
	@Max(99999)
	@Parameter(description = "数量", allowableValues = "range[-99999,99999]", required = true)
	private int quantity;

	/**
	 * 見積単価
	 */
	@Parameter(description = "見積単価", allowableValues = "range[0.00,9999999999999999999.99]", required = true)
	private BigDecimal unitPrice;

	/**
	 * 見積金額
	 */
	@NotNull
	@Digits(integer = 19, fraction = 2)
	@Parameter(description = "見積金額", allowableValues = "range[-9999999999999999999.99,9999999999999999999.99]", required = true)
	private BigDecimal amountSummary;

	/**
	 * R原価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Parameter(description = "R原価", allowableValues = "range[0.00,9999999999999999999.99]", required = true)
	private BigDecimal rCost;

	/**
	 * ＲＪ仕入価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Parameter(description = "ＲＪ仕入価格", allowableValues = "range[0.00,9999999999999999999.99]", required = true)
	private BigDecimal rjPurchasePrice;

	/**
	 * ＲＪ仕切価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Parameter(description = "ＲＪ仕切価格", allowableValues = "range[0.00,9999999999999999999.99]", required = true)
	private BigDecimal rjDividingPrice;

	/**
	 * 母店売価(接点店仕切)
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Parameter(description = "母店売価(接点店仕切)", allowableValues = "range[0.00,9999999999999999999.99]", required = true)
	private BigDecimal motherStorePrice;

	/**
	 * 契約期間
	 */
	@Parameter(description = "契約期間", required = false)
	private String contractSpan;

	/**
	 * 拡張項目
	 */
	@Parameter(description = "拡張項目", required = false)
	@Lob
	private String extendsParameter;

	/**
	 * 契約数
	 */
	@Max(99999)
	@Min(-99999)
	@Schema(description = "契約数", allowableValues = "range[-99999,99999]", required = false)
	private Integer contractAmount;

	/**
	 * 価格改定前リコー品種コード
	 */
	@Size(max = 255)
	@Parameter(description = "価格改定前リコー品種コード", allowableValues = "range[0,255]", required = false)
	private String bfPriceRevisionItemCode;
}
