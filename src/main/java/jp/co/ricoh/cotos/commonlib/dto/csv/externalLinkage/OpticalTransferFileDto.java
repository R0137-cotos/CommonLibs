package jp.co.ricoh.cotos.commonlib.dto.csv.externalLinkage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "お客様ID", "文書番号", "ステータス", "注文分類", "工事調整完了番号" })
public class OpticalTransferFileDto {
	/**
	 * お客様ID
	 */
	@JsonProperty("お客様ID")
	private String customerId;

	/**
	 * 文書番号
	 */
	@JsonProperty("文書番号")
	private String documentNumber;

	/**
	 * ステータス
	 */
	@JsonProperty("ステータス")
	private String status;

	/**
	 * 注文分類
	 */
	@JsonProperty("注文分類")
	private String orderType;

	/**
	 * 工事調整完了番号
	 */
	@JsonProperty("工事調整完了番号")
	private String constructAdjustmentNumber;
}
