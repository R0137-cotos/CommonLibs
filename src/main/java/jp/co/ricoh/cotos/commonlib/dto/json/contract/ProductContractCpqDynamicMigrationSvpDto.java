package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 商品（契約用）拡張項目CPQ動的移行用DTO（SVP）
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
	/**
	 * 使用OS区分
	 */
	private String useOsDiv;
}
