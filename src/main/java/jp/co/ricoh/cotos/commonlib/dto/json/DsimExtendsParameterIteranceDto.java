package jp.co.ricoh.cotos.commonlib.dto.json;

import lombok.Data;

/**
 * ドコモSIM拡張項目繰返DTO
 * 商品(見積)・商品(契約)共に共通
 */
@Data
public class DsimExtendsParameterIteranceDto {

	private long id;

	/**
	 * 種別
	 */
	private String contractType;

	/**
	 * 商品コード
	 */
	private String productCode;

	/**
	 * 商品名
	 */
	private String productName;

	/**
	 * MNP
	 */
	private String mnpType;

	/**
	 * 回線番号
	 */
	private String lineNumber;

	/**
	 * SIM番号（シリアル番号）
	 */
	private String serialNumber;

	/**
	 * IMEI（携帯シリアル番号）
	 */
	private String imeiNumber;

	/**
	 * 送り状番号
	 */
	private String invoiceNumber;

	/**
	 * 解約日
	 */
	private String cancelDate;

	/**
	 * 承諾番号
	 */
	private String mnpNumber;

	/**
	 * 承諾番号 発行日
	 */
	private String mnpIssueDate;

	/**
	 * 承諾番号 有効期限
	 */
	private String mnpExpirationDate;

	/**
	 * 切替日
	 */
	private String switchingDate;

	/**
	 * モバイルAPIプランID
	 */
	private String planId;

	/**
	 * 使用デバイス
	 */
	private String device;

	/**
	 * 納入予定日
	 */
	private String deliveryExpectedDate;
}
