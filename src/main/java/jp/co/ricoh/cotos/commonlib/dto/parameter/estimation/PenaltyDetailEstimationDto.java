package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class PenaltyDetailEstimationDto extends DtoBase {
	/**
	 * 品種マスタID
	 */
	@Min(0)
	@Column(nullable = false)
	@ApiModelProperty(value = "品種マスタID", required = true, position = 3, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * 品種名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "品種名", required = false, position = 4, allowableValues = "range[0,255]")
	private String itemName;

	/**
	 * 違約金単価
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "違約金単価", required = false, position = 5, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal penaltyUnitPrice;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(0)
	@ApiModelProperty(value = "数量", required = false, position = 6, allowableValues = "range[0,99999]")
	private int quantity;

	/**
	 * 違約金額
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "違約金額", required = false, position = 7, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal penaltyAmountSummary;

	/**
	 * 削除フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "削除フラグ", required = false, position = 8, allowableValues = "range[0,9]")
	private Integer deleteFlg;

	/**
	 * 違約金発生解約最終日
	 */
	@ApiModelProperty(value = "違約金発生解約最終日", required = false, position = 9)
	@Temporal(TemporalType.DATE)
	private Date penaltyOccurCacnlLastDate;
}
