package jp.co.ricoh.cotos.commonlib.dto.csv.accounting;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "契約No.", "商品コード", "商品名", "回線番号", "シリアル番号", "連携月", "契約単価", "利用月", "数量", "単価", "税区分", "税率（％）", "消費税", "金額" })
public class InvoiceFileDto {
	/**
	 * 契約No.
	 */
	@JsonProperty("契約No.")
	private String contractId;

	/**
	 * 商品コード
	 */
	@JsonProperty("商品コード")
	private String ricohItemCode;

	/**
	 * 商品名
	 */
	@JsonProperty("商品名")
	private String itemContractName;

	/**
	 * 回線番号
	 */
	@JsonProperty("回線番号")
	private String lineNumber;

	/**
	 * シリアル番号
	 */
	@JsonProperty("シリアル番号")
	private String serialNumber;

	/**
	 * 連携月
	 */
	@JsonProperty("連携月")
	private String createYm;

	/**
	 * 契約単価
	 */
	@JsonProperty("契約単価")
	private String orderPrice;

	/**
	 * 利用月
	 */
	@JsonProperty("利用月")
	private String billingDate;

	/**
	 * 数量
	 */
	@JsonProperty("数量")
	private String salesCnt;

	/**
	 * 単価
	 */
	@JsonProperty("単価")
	private BigDecimal salesPrice;

	/**
	 * 税区分
	 */
	@JsonProperty("税区分")
	private String salesTaxType;

	/**
	 * 税率（％）
	 */
	@JsonProperty("税率（％）")
	private String salesTaxRate;

	/**
	 * 消費税
	 */
	@JsonProperty("消費税")
	private BigDecimal salesTaxPrice;

	/**
	 * 金額
	 */
	@JsonProperty("金額")
	private BigDecimal remarks;
}
