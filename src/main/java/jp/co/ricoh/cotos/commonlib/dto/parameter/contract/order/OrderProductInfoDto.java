package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.order;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.contract.order.OrderProductInfo.ChargeRule;
import jp.co.ricoh.cotos.commonlib.entity.contract.order.OrderProductInfo.ProvideMethod;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 注文商品情報DTO
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class OrderProductInfoDto {

	/**
	 * 商品コード（RICOH品種コード）
	 */
	@Size(max = 255)
	@Schema(description = "商品コード（RICOH品種コード）", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String productCd;

	/**
	 * 課金制約ルール
	 */
	@Schema(description = "課金制約ルール", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "有料(\"0\"), 初月無料(\"1\"), 無料期間指定(\"2\")")
	private ChargeRule chargeRule;

	/**
	 * 無料期間
	 */
	@Size(max = 255)
	@Schema(description = "無料期間", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String freePeriod;

	/**
	 * 商品名
	 */
	@Size(max = 255)
	@Schema(description = "商品名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String productName;

	/**
	 * 提供方法
	 */
	@Schema(description = "提供方法", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "初期(\"1\"), 月額(\"2\"), 年額(\"3\")")
	private ProvideMethod provideMethod;

	/**
	 * 変更後数量
	 */
	@Min(0)
	@Max(99999)
	@Schema(description = "変更後数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer quantity;

	/**
	 * 変更前数量
	 */
	@Min(0)
	@Max(99999)
	@Schema(description = "変更前数量", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,99999]")
	private Integer beforeQuantity;

	/**
	 * 差分
	 */
	@Max(99999)
	@Schema(description = "差分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer differenceQuantity;

	/**
	 * 売価単価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "売価単価", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal unitPrice;

	/**
	 * 売価合計
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "売価合計", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal amountSummary;

}
