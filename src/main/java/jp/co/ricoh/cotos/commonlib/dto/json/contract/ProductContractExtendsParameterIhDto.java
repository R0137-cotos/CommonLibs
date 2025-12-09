package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 商品（契約用）拡張項目DTO（IH）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractExtendsParameterIhDto {
	/**
	 * 移行用項目
	 */
	@JsonProperty("migrationParameter")
	private ProductContractMigrationParameterDto productContractMigrationParameterDto;
}
