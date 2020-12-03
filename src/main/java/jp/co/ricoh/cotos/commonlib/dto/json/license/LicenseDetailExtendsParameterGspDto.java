package jp.co.ricoh.cotos.commonlib.dto.json.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * ライセンス明細拡張項目DTO（GSP）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseDetailExtendsParameterGspDto {

	/**
	 * 受付状況フラグ
	 */
	private String receptionStatusFlg;

	/**
	 * 不受理理由
	 */
	private String notAcceptReason;

	/**
	 * 利用期間開始日
	 */
	private String usePeriodStart;

	/**
	 * 利用期間終了日
	 */
	private String usePeriodEnd;

	/**
	 * サービス種別
	 */
	private String serviceType;

	/**
	 * 商品区分
	 */
	private String productType;

	/**
	 * 既存契約商品区分
	 */
	private String oldProductType;
}
