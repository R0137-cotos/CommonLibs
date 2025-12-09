package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 商品（見積用）拡張項目DTO（SVP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductEstimationExtendsParameterSvpDto {

	/**
	 * CPQ商品固有戻り値
	 */
	@JsonProperty("cpqProductSpecificReturnValue")
	private CpqReturnValueSvpDto cpqReturnValueSvpDto;

	/**
	 * CPQ動的移行用
	 */
	@JsonProperty("cpqDynamicMigration")
	private ProductEstimationCpqDynamicMigrationSvpDto cpqDynamicMigrationDto;

	/**
	 * 移行用項目
	 */
	@JsonProperty("migrationParameter")
	private ProductEstimationMigrationParameterDto productEstimationMigrationParameterDto;
}
