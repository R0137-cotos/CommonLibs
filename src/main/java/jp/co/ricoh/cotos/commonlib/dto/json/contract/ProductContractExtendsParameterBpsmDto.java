package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.RtorFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.VendorDiv;
import lombok.Data;

/**
 * 商品（契約用）拡張項目DTO（BPSM）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractExtendsParameterBpsmDto {
	/**
	 * 契約種別詳細
	 */
	private ContractTypeDetails contractTypeDetails;

	/**
	 * ベンダー区分
	 */
	private VendorDiv vendorDiv;

	/**
	 * RtoRフラグ
	 */
	private RtorFlg rtorFlg;

	/**
	 * サービスコードKD
	 */
	private String serviceCdKd;

	/**
	 * アップグレード元RJ管理番号
	 */
	private String upgradeOriginRjManageNumber;

	/**
	 * アップグレード先RJ管理番号
	 */
	private String upgradeRjManageNumber;

	/**
	 * 移行用項目
	 */
	@JsonProperty("migrationParameter")
	private ProductContractMigrationParameterDto productContractMigrationParameterDto;
}
