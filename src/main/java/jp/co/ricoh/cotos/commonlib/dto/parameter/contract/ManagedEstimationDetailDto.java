package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import java.math.BigDecimal;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.DetailStatus;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationDetail.IncreaseDecreaseDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ManagedEstimationDetailDto extends DtoBase {
	/**
	 * 状態
	 */
	@NotNull
	@ApiModelProperty(value = "状態", required = true, allowableValues = "NOUPDATE(\"1\"), ADD(\"2\"), DELETE(\"3\"), UPDATE(\"4\")", example = "1", position = 3)
	private DetailStatus state;

	/**
	 * 変更前数量
	 */
	@Max(99999)
	@Min(-99999)
	@ApiModelProperty(value = "変更前数量", required = false, position = 4, allowableValues = "range[-99999,99999]")
	private Integer beforeQuantity;

	/**
	 * 数量
	 */
	@Min(-99999)
	@Max(99999)
	@ApiModelProperty(value = "数量", required = true, position = 5, allowableValues = "range[-99999,99999]")
	private int quantity;

	/**
	 * 見積単価
	 */
	@NotNull
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "見積単価", required = true, position = 6, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal estimationUnitPrice;

	/**
	 * 見積金額
	 */
	@NotNull
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "見積金額", required = true, position = 7, allowableValues = "range[-9999999999999999999.99,9999999999999999999.99]")
	private BigDecimal estimationAmountSummary;

	/**
	 * 摘要
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "摘要", required = false, position = 8, allowableValues = "range[0,255]")
	private String detailAbstract;

	/**
	 * 拡張項目
	 */
	@Lob
	@ApiModelProperty(value = "拡張項目", required = false, position = 9)
	private String extendsParameter;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "品種マスタID", required = true, position = 10, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * リコー品種コード
	 */
	@NotNull
	@Size(max = 255)
	@ApiModelProperty(value = "リコー品種コード", required = true, position = 11, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * メーカー商品コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メーカー商品コード", required = false, position = 12, allowableValues = "range[0,255]")
	private String makerItemCode;

	/**
	 * 変更前単価
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "変更前単価", required = false, position = 13, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal beforeUnitPrice;

	/**
	 * 増減区分
	 */
	@ApiModelProperty(value = "増減区分", required = false, allowableValues = "増数(\"1\"), 減数(\"2\")", example = "1", position = 14)
	private IncreaseDecreaseDiv increaseDecreaseDiv;
}
