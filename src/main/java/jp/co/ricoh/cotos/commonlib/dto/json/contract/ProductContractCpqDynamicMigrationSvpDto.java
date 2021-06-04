package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 契約機種拡張項目DTO（PCP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractCpqDynamicMigrationSvpDto {
	/**
	 * ソフトウェアクラス区分
	 */
	private String softwareClassDiv;

	/**
	 * ソフトウェア数量
	 */
	private Integer softwareQuantity;

	/**
	 * 更新フラグ
	 */
	private String updateFlg;
}
