package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class O365LatestContractDetailDto {

	/**
	 * 品種名
	 */
	@Schema(description = "品種名", required = false)
	private String itemContractName;

	/**
	 * リコー品種コード
	 */
	@Schema(description = "リコー品種コード", required = false)
	private String ricohItemCode;

	/**
	 * 年契約・月契約区分
	 */
	@Schema(description = "年契約・月契約区分", required = false)
	private String yearMonthContractDiv;

	/**
	 * ライセンス開始日
	 */
	@Schema(description = "ライセンス開始日", required = false)
	String licenseStartDate;

	/**
	 * 単価
	 */
	@Schema(description = "単価", required = false)
	private int unitPrice;

	/**
	 * 課金開始年月
	 */
	@Schema(description = "課金開始年月", required = false)
	private String billingStartMonth;

	/**
	 * 自動更新日
	 */
	@Schema(description = "自動更新日", required = false)
	private String automaticUpdateDate;

	/**
	 * 契約数量
	 */
	@Schema(description = "契約数量", required = false)
	private int quantity;

	/**
	 * 申し込み数量
	 */
	@Schema(description = "申し込み数量", required = false)
	private int orderQuantity;

	/**
	 * 変更前数量
	 */
	@Schema(description = "変更前数量", required = false)
	private int beforeQuantity;

	/**
	 * 減数反映日
	 */
	@Schema(description = "減数反映日", required = false)
	private String reductionReflectionDate;

	/**
	 * 減数可能期間（From）
	 */
	@Schema(description = "減数可能期間（From）", required = false)
	private String decreasablePeriodStart;

	/**
	 * 減数可能期間（To）
	 */
	@Schema(description = "減数可能期間（To）", required = false)
	private String decreasablePeriodEnd;

	/**
	 * 年額計上月
	 */
	@Schema(description = "年額計上月", required = false)
	private String annualAmountRecordMonth;

}