package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 商品（見積用）拡張項目O365商品固有戻り値
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqUpgradeItemO365Dto {

	/**
	 * 移行元品種マスタID
	 */
	private Long fromItemMasterId;

	/**
	 * 移行元品種コード
	 */
	private String fromRicohItemCode;

	/**
	 * 移行先品種マスタID
	 */
	private Long toItemMasterId;

	/**
	 * 移行先品種コード
	 */
	private String toRicohItemCode;

	/**
	 * 移行数量
	 */
	private Integer quantity;
}
