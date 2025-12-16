package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.order;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.contract.order.OrderBasicInfo.CommercialFlowDiv;
import jp.co.ricoh.cotos.commonlib.entity.contract.order.OrderBasicInfo.OrdererType;
import jp.co.ricoh.cotos.commonlib.entity.contract.order.OrderBasicInfo.ProductType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 注文基底情報
 * @author z00se03039
 *
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class OrderBasicContentsDto {
	/**
	 * 注文番号
	 */
	@Size(max = 255)
	@Schema(description = "注文番号", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String ordererNumber;

	/**
	 * 注文タイプ
	 */
	@Schema(description = "注文タイプ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "新規(\"1\"), 変更(\"2\"), 解約(\"3\"), キャンセル(\"4\")")
	private OrdererType ordererType;

	/**
	 * 商品種別
	 */
	@Schema(description = "商品種別", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "RSI(\"1\"), ROC(\"2\"), O365(\"3\"), MVB(\"4\")")
	private ProductType productType;

	/**
	 * 契約番号
	 */
	@Size(max = 255)
	@Schema(description = "契約番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * メーカー契約番号
	 */
	@Size(max = 255)
	@Schema(description = "メーカー契約番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String makerManageNumber;

	/**
	 * 商流区分（代直区分）
	 */
	@Schema(description = "商流区分（代直区分）", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "代売(\"1\"), 直売(\"2\")")
	private CommercialFlowDiv commercialFlowDiv;

	/**
	 * 申込日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "申込日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Tokyo")
	private Date ordererDateTime;

	/**
	 * 解約予定日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "解約予定日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date cancelScheduledDate;

	/**
	 * 解約理由1
	 */
	@Size(max = 255)
	@Schema(description = "解約理由1", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cancelReason1;

	/**
	 * 解約理由2
	 */
	@Size(max = 1000)
	@Schema(description = "解約理由2", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1000]")
	private String cancelReason2;

	/**
	 * 改善ポイント
	 */
	@Size(max = 1000)
	@Schema(description = "改善ポイント", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1000]")
	private String improvementPoint;

	/**
	 * 初期費合計
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "初期費合計", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal initialTotalAmount;

	/**
	 * 月額費合計
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "月額費合計", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal monthlyTotalAmount;

	/**
	 * 年額費合計
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "年額費合計", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal yearlyTotalAmount;
}
