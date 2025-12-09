package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 商品（契約用）拡張項目DTO（RH）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractExtendsParameterRhDto {
	/**
	 * 違約金計算起算日
	 */
	private String penaltyStartDate;

	/**
	 * 回線種別区分
	 */
	private String lineTypeDiv;

	/**
	 * 回線種別名
	 */
	private String lineTypeName;

	/**
	 * NTTお客様ID
	 */
	private String nttCustomerId;

	/**
	 * 移行用項目
	 */
	@JsonProperty("migrationParameter")
	private ProductContractMigrationParameterDto productContractMigrationParameterDto;
}
