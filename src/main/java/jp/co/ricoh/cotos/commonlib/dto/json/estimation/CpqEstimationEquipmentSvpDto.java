package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ商品固有戻り値 見積機種リストDTO（SVP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqEstimationEquipmentSvpDto {

	/**
	 * 機器区分
	 */
	private String machineType;

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
	private String modelNumber;

	/**
	 * 製品名
	 */
	private String offeringsName;

	/**
	 * 使用OS
	 */
	private String useOs;

	/**
	 * ハードウェア購入日
	 */
	private Date hardwarePurchaseDate;

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
	 * 請求方法区分
	 */
	private String billing_type;

	/**
	 * メーカーコード
	 */
	private String makerCode;

	/**
	 * メーカー名
	 */
	private String makerName;

	/**
	 * メニュー区分
	 */
	private String menuType;

	/**
	 * メニュー名
	 */
	private String menuName;

	/**
	 * 保守対応時間区分
	 */
	private String maintenanceTimeType;
}
