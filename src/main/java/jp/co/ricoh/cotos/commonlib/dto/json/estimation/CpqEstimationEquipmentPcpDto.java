package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ商品固有戻り値 見積機種リストDTO（PCP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqEstimationEquipmentPcpDto {

	/**
	 * 機種コード
	 */
	private String equipmentCode;

	/**
	 * 機種名
	 */
	private String equipmentName;

	/**
	 * 型番
	 */
	private String ｍodelNumber;

	/**
	 * メーカー名
	 */
	private String makerName;

	/**
	 * 購入日
	 */
	private Date purchaseDate;

	/**
	 * 保守終了日
	 */
	private Date maintenanceEndDate;

	/**
	 * CPQマッピングキー
	 */
	private String cpqMappingKey;

	/**
	 * 数量
	 */
	private Integer quantity;

	/**
	 * 契約年数
	 */
	private String contractYears;

	/**
	 * 品種コード
	 */
	private String itemCode;

	/**
	 * メーカーコード
	 */
	private String makerCode;

	/**
	 * 分類コード
	 */
	private String classificationCode;

	/**
	 * 分類名
	 */
	private String classificationName;

	/**
	 * 保守可能年数
	 */
	private String maintenanceYears;

	/**
	 * 開梱設定フラグ
	 */
	private String installationFlg;

	/**
	 * ネットワーク設定フラグ
	 */
	private String networkFlg;

	/**
	 * ネットワーク設定品種コード
	 */
	private String networkItemCode;

	/**
	 * ソフトインストールフラグ
	 */
	private String installFlg;

	/**
	 * ソフトインストール品種コード
	 */
	private String installItemCode;
}
