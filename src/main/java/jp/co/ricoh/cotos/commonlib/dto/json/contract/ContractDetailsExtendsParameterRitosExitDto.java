package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 契約明細拡張項目DTO（脱RITOS商品）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractDetailsExtendsParameterRitosExitDto {
	/**
	 * RITOS移行用
	 */
	@JsonProperty("ritosExitMigration")
	private ContractDetailsRitosExitMigrationDto contractDetailsRitosExitMigrationDto;
}
