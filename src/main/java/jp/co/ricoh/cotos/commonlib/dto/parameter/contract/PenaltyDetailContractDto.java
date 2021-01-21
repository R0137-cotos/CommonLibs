package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.PenaltyDetailContract.PenaltyAccountSalesStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.PenaltyDetailContract.SalesToType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class PenaltyDetailContractDto extends DtoBase {

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "品種マスタID", required = true, position = 1, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * 品種名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "品種名", required = false, position = 2, allowableValues = "range[0,255]")
	private String itemName;

	/**
	 * リコー品種コード
	 */
	@Size(max = 255)
	@NotNull
	@ApiModelProperty(value = "リコー品種コード", required = true, position = 3, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 品種区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "品種区分", required = false, position = 4, allowableValues = "range[0,255]")
	private String itemType;

	/**
	 * 違約金単価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "違約金単価", required = false, position = 5, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal penaltyUnitPrice;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(0)
	@ApiModelProperty(value = "数量", required = false, position = 6, allowableValues = "range[0,99999]")
	private Integer quantity;

	/**
	 * 違約金金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "違約金金額", required = false, position = 7, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal penaltyAmountSummary;

	/**
	 * 計上先区分
	 */
	@ApiModelProperty(value = "計上先区分", required = false, allowableValues = "エンドユーザ(\"1\"), 課所止め(\"2\")", example = "1", position = 8)
	private SalesToType salesToType;

	/**
	 * 削除フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "削除フラグ", required = false, position = 9, allowableValues = "range[0,9]")
	private Integer deleteFlg;

	/**
	 * 違約金売上計上処理状態
	 */
	@ApiModelProperty(value = "違約金売上計上処理状態", required = false, allowableValues = "未計上(\"0\"), 計上済み(\"1\"), 処理不要(\"2\"), 処理不可(\"3\")", example = "1", position = 10)
	private PenaltyAccountSalesStatus penaltyAccountSalesStatus;

	/**
	 * 違約金売上計上処理日
	 */
	@ApiModelProperty(value = "違約金売上計上処理日", required = false, position = 11)
	@Temporal(TemporalType.DATE)
	private Date penaltyAccountSalesDate;

}
