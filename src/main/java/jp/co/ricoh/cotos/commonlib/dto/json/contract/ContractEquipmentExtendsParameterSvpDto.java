package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeSvp.MaintenanceTimeTypeSvp;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeSvp.MenuTypeSvp;
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
	 * ハードウェア購入日
	 */
	private String hardwarePurchaseDate;

	/**
	 * メーカー名
	 */
	private String makerName;

	/**
	 * 型番
	 */
	private String modelNumber;

	/**
	 * メニュー区分
	 */
	private MenuTypeSvp menuType;

	/**
	 * メニュー名
	 */
	private String menuName;

	/**
	 * 保守対応時間区分
	 */
	private MaintenanceTimeTypeSvp maintenanceTimeType;

	/**
	 * CPQマッピングキー
	 */
	private String cpqMappingKey;

	/**
	 * CPQ動的移行用
	 */
	@JsonProperty("cpqDynamicMigration")
	private ContractEquipmentCpqDynamicMigrationSvpDto contractEquipmentCpqDynamicMigrationDto;
}
