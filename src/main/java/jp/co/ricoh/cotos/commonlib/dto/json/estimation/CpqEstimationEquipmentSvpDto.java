package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeSvp.MachineTypeSvp;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeSvp.MaintenanceTimeTypeSvp;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeSvp.MakerCodeSvp;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeSvp.MenuTypeSvp;
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
	private MachineTypeSvp machineType;

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
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
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
	 * メーカーコード
	 */
	private MakerCodeSvp makerCode;

	/**
	 * メーカー名
	 */
	private String makerName;

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
}
