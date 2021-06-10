package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 見積明細拡張項目RITOS移行用DTO（脱RITOS商品）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EstimationDetailsRitosExitMigrationDto {
	/**
	 * RITOS文書番号
	 */
	private String ritosDocumentNumber;

	/**
	 * 基本契約通し番号
	 */
	private Integer basicContractSerialNumber;
}
