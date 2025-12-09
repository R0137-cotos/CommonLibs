package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "品種マスタID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * 品種名
	 */
	@Size(max = 255)
	@Schema(description = "品種名", required = false, allowableValues = "range[0,255]")
	private String itemName;

	/**
	 * リコー品種コード
	 */
	@Size(max = 255)
	@NotNull
	@Schema(description = "リコー品種コード", required = true, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 品種区分
	 */
	@Size(max = 255)
	@Schema(description = "品種区分", required = false, allowableValues = "range[0,255]")
	private String itemType;

	/**
	 * 違約金単価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "違約金単価", required = false, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal penaltyUnitPrice;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "数量", required = false, allowableValues = "range[0,99999]")
	private Integer quantity;

	/**
	 * 違約金金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "違約金金額", required = false, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal penaltyAmountSummary;

	/**
	 * 計上先区分
	 */
	@Schema(description = "計上先区分", required = false, allowableValues = "エンドユーザ(\"1\"), 課所止め(\"2\")", example = "1")
	private SalesToType salesToType;

	/**
	 * 削除フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "削除フラグ", required = false, allowableValues = "range[0,9]")
	private Integer deleteFlg;

	/**
	 * 違約金売上計上処理状態
	 */
	@Schema(description = "違約金売上計上処理状態", required = false, allowableValues = "未計上(\"0\"), 計上済み(\"1\"), 処理不要(\"2\"), 処理不可(\"3\")", example = "1")
	private PenaltyAccountSalesStatus penaltyAccountSalesStatus;

	/**
	 * 違約金売上計上処理日
	 */
	@Schema(description = "違約金売上計上処理日", required = false)
	@Temporal(TemporalType.DATE)
	private Date penaltyAccountSalesDate;

}
