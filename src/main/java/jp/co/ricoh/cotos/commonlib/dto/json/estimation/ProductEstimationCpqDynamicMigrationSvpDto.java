package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeSvp.BillingTypeSvp;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeSvp.MaintenanceTimeTypeSvp;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeSvp.MenuTypeSvp;
import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ動的移行用DTO（SVP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductEstimationCpqDynamicMigrationSvpDto {

	/**
	 * 変更元契約種別詳細
	 */
	private ContractTypeDetails originContractTypeDetails;

	/**
	 * ハードウェア購入日
	 */
	private String hardwarePurchaseDate;

	/**
	 * メニュー区分
	 */
	private MenuTypeSvp menuType;

	/**
	 * 契約年数
	 */
	private Integer contractYears;

	/**
	 * 請求方法区分
	 */
	private BillingTypeSvp billingType;

	/**
	 * メーカーコード
	 */
	private String makerCode;

	/**
	 * 型番
	 */
	private String modelNumber;

	/**
	 * 本体クラス区分
	 */
	private String bodyClassDiv;

	/**
	 * 本体製品名
	 */
	private String bodyOfferingsName;

	/**
	 * 保守対応時間
	 */
	private MaintenanceTimeTypeSvp maintenanceTimeType;

	/**
	 * 使用OS区分
	 */
	private String useOsDiv;

	/**
	 * ソフトウェアクラス区分
	 */
	private String softwareClassDiv;

	/**
	 * ソフトウェア数量
	 */
	private Integer softwareQuantity;

	/**
	 * 監視運用オプション区分
	 */
	private String monitoringOpe;

	/**
	 * 監視運用オプション導入希望日
	 */
	private String monitoringOpeIntPreferredDate;

	/**
	 * 更新品種フラグ
	 */
	private String updateItemFlg;

	/**
	 * ハードウェア情報
	 */
	@JsonProperty("hardwareInfo")
	private List<CpqHardwareInfoSvpDto> cpqHardwareInfoSvpDtoList;
}
