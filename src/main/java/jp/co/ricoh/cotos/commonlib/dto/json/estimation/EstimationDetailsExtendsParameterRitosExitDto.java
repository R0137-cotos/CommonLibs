package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 見積明細拡張項目DTO（脱RITOS商品）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EstimationDetailsExtendsParameterRitosExitDto {
	/**
	 * RITOS移行用
	 */
	@JsonProperty("ritosExitMigration")
	private EstimationDetailsRitosExitMigrationDto estimationDetailsRitosExitMigrationDto;
}
