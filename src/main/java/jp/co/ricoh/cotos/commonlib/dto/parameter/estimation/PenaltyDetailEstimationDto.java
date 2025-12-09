package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "品種マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * 品種名
	 */
	@Size(max = 255)
	@Schema(description = "品種名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String itemName;

	/**
	 * 違約金単価
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "違約金単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal penaltyUnitPrice;

	/**
	 * 数量
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private int quantity;

	/**
	 * 違約金額
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "違約金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal penaltyAmountSummary;

	/**
	 * 削除フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "削除フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer deleteFlg;

	/**
	 * 違約金発生解約最終日
	 */
	@Schema(description = "違約金発生解約最終日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date penaltyOccurCacnlLastDate;
}
