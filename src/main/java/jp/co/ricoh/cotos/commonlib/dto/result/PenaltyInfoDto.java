package jp.co.ricoh.cotos.commonlib.dto.result;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.ItemType;
import lombok.Data;

/**
 * 違約金情報を保持するDto
 */
@Data
public class PenaltyInfoDto {

	/**
	 * 違約金品種マスタID
	 */
	@ApiModelProperty(value = "違約金品種マスタID",  required = false, position = 1, allowableValues = "range[0,9223372036854775807]")
	private Long penaltyItemMasterId;

	/**
	 * 違約金品種名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "違約金品種名", required = false, position = 2, allowableValues = "range[0,255]")
	private String penaltyItemName;

	/**
	 * 違約金リコー品種コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "違約金リコー品種コード", required = false, position = 3, allowableValues = "range[0,255]")
	private String penaltyRicohItemCode;

	/**
	 * 違約金品種区分
	 */
	@ApiModelProperty(value = "違約金品種区分", required = true, allowableValues = "なし(\"0\"), 基本(\"1\"), オプション(\"2\")", example = "1", position = 4)
	private ItemType penaltyItemType;

	/**
	 * 元品種マスタID
	 */
	@ApiModelProperty(value = "元品種マスタID",  required = false, position = 5, allowableValues = "range[0,9223372036854775807]")
	private Long originItemMasterId;

	/**
	 * 違約金単価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "違約金単価", required = true, position = 6, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal penaltyUnitPrice;

	/**
	 * 数量
	 */
	@Min(0)
	@Max(99999)
	@ApiModelProperty(value = "数量", required = true, position = 7, allowableValues = "range[0,99999]")
	private int quantity;

	/**
	 * 違約金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "違約金額", required = true, position = 8, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal penaltyAmountSummary;

	/**
	 * 違約金発生解約最終日
	 */
	@ApiModelProperty(value = "違約金発生解約最終日", required = false, position = 9)
	@Temporal(TemporalType.DATE)
	private Date penaltyOccurCacnlLastDate;
}
