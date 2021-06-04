package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 契約機種拡張項目DTO（PCP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractEquipmentCpqDynamicMigrationSvpDto {
	/**
	 * クラス区分
	 */
	private String classDiv;

	/**
	 * ハードウェア分類コード
	 */
	private String hardwareClassificCode;
}
