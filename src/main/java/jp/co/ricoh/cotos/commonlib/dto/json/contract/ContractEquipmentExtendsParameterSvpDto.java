package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 契約機種拡張項目DTO（SVP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractEquipmentExtendsParameterSvpDto {
	/**
	 * 枝番
	 */
	private String branchesNumber;

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
	private String hardwarePurchaseDate;

	/**
	 * 請求方法区分
	 */
	private String billing_type;

	/**
	 * メーカー名
	 */
	private String makerName;

	/**
	 * 型番
	 */
	private String ｍodelNumber;

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

	/**
	 * 保証開始日
	 */
	private String maintenanceStartDate;

	/**
	 * ハードディスク
	 */
	private String harddiskType;
}
