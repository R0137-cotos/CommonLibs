package jp.co.ricoh.cotos.commonlib.dto.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class O365LatestContractDetailDto {

	/**
	 * 品種名
	 */
	@ApiModelProperty(value = "品種名", required = false, position = 1)
	private String itemContractName;

	/**
	 * リコー品種コード
	 */
	@ApiModelProperty(value = "リコー品種コード", required = false, position = 2)
	private String ricohItemCode;

	/**
	 * 年契約・月契約区分
	 */
	@ApiModelProperty(value = "年契約・月契約区分", required = false, position = 3)
	private String yearMonthContractDiv;

	/**
	 * ライセンス開始日
	 */
	@ApiModelProperty(value = "ライセンス開始日", required = false, position = 4)
	String licenseStartDate;

	/**
	 * 単価
	 */
	@ApiModelProperty(value = "単価", required = false, position = 5)
	private int unitPrice;

	/**
	 * 課金開始年月
	 */
	@ApiModelProperty(value = "課金開始年月", required = false, position = 5)
	private String billingStartMonth;

	/**
	 * 自動更新日
	 */
	@ApiModelProperty(value = "自動更新日", required = false, position = 6)
	private String automaticUpdateDate;

	/**
	 * 契約数量
	 */
	@ApiModelProperty(value = "契約数量", required = false, position = 7)
	private int quantity;

	/**
	 * 申し込み数量
	 */
	@ApiModelProperty(value = "申し込み数量", required = false, position = 8)
	private int orderQuantity;

	/**
	 * 変更前数量
	 */
	@ApiModelProperty(value = "変更前数量", required = false, position = 9)
	private int beforeQuantity;

	/**
	 * 減数反映日
	 */
	@ApiModelProperty(value = "減数反映日", required = false, position = 10)
	private String reductionReflectionDate;

	/**
	 * 減数可能期間（From）
	 */
	@ApiModelProperty(value = "減数可能期間（From）", required = false, position = 11)
	private String decreasablePeriodStart;

	/**
	 * 減数可能期間（To）
	 */
	@ApiModelProperty(value = "減数可能期間（To）", required = false, position = 12)
	private String decreasablePeriodEnd;

}