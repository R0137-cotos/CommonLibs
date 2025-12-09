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

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "違約金品種マスタID",  requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long penaltyItemMasterId;

	/**
	 * 違約金品種名
	 */
	@Size(max = 255)
	@Schema(description = "違約金品種名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String penaltyItemName;

	/**
	 * 違約金リコー品種コード
	 */
	@Size(max = 255)
	@Schema(description = "違約金リコー品種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String penaltyRicohItemCode;

	/**
	 * 違約金品種区分
	 */
	@Schema(description = "違約金品種区分", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "なし(\"0\"), 基本(\"1\"), オプション(\"2\")", example = "1")
	private ItemType penaltyItemType;

	/**
	 * 元品種マスタID
	 */
	@Schema(description = "元品種マスタID",  requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long originItemMasterId;

	/**
	 * 違約金単価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "違約金単価", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal penaltyUnitPrice;

	/**
	 * 数量
	 */
	@Min(0)
	@Max(99999)
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,99999]")
	private int quantity;

	/**
	 * 違約金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "違約金額", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal penaltyAmountSummary;

	/**
	 * 違約金発生解約最終日
	 */
	@Schema(description = "違約金発生解約最終日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date penaltyOccurCacnlLastDate;
}
