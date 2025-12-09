package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeLanf2.ExternalHardStackDiv;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeLanf2.PcBackupStackDiv;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeLanf2.UpsStackDiv;
import lombok.Data;

/**
 * 商品（契約用）拡張項目DTO（LANF2）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractExtendsParameterLanf2Dto {
	/**
	 * 契約種別詳細
	 */
	private ContractTypeDetails contractTypeDetails;

	/**
	 * 外付けHDD積上げ区分
	 */
	private ExternalHardStackDiv externalHardStackDiv;

	/**
	 * UPS積上げ区分
	 */
	private UpsStackDiv upsStackDiv;

	/**
	 * PCバックアップ積上げ区分
	 */
	private PcBackupStackDiv pcBackupStackDiv;

	/**
	 * 移行用項目
	 */
	@JsonProperty("migrationParameter")
	private ProductContractMigrationParameterDto productContractMigrationParameterDto;
}
