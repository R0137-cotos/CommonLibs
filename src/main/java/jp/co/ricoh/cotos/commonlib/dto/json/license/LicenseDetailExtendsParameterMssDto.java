package jp.co.ricoh.cotos.commonlib.dto.json.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * ライセンス明細拡張項目DTO（MSS）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseDetailExtendsParameterMssDto {

	/**
	 * CSV用ライセンス種別
	 */
	private String csvLicenseType;

	/**
	 * 変更前数量
	 */
	private String beforeQuantity;

	/**
	 * ライセンスキー
	 */
	private String licenseKey;

	/**
	 * 課金開始月
	 */
	private String paymentStartMonth;

	/**
	 * シリアル番号
	 */
	private String serialNumber;

	/**
	 * 荷番
	 */
	private String baggageNumber;

	/**
	 * 着荷予定日
	 */
	private String baggageArrivalScheduledDate;

	/**
	 * DIS様回答日
	 */
	private String disAnswerDate;

	/**
	 * POナンバー
	 */
	private String poNumber;
}
