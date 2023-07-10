package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.AddSubdomainFlg;
import lombok.Data;

/**
 * 商品（契約用）拡張項目DTO（MWOD）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractExtendsParameterMwodDto {
	/**
	 * 契約種別詳細
	 */
	private ContractTypeDetails contractTypeDetails;

	/**
	 * サブドメイン追加フラグ
	 */
	private AddSubdomainFlg addSubdomainFlg;

	/**
	 * RtoR固有項目
	 */
	@JsonProperty("rtorSpecial")
	private ProductContractRtorSpecialMwodDto productContractRtorSpecialMwodDto;

	/**
	 * 移行用項目
	 */
	@JsonProperty("migrationParameter")
	private ProductContractMigrationParameterDto productContractMigrationParameterDto;

	/**
	 * サブドメインWebホスティング数量
	 */
	private int subdomainWebQuantity;

	/**
	 * サブドメインメールホスティング数量
	 */
	private int subdomainMailQuantity;
}
