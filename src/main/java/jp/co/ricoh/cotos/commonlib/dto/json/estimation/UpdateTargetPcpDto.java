package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 商品（見積用）拡張項目共通商品固有項目_更新対象DTO（PCP）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateTargetPcpDto {

	/**
	 * 品種コード
	 */
	private String itemCode;

	/**
	 * 変更元品種コード
	 */
	private String originItemCode;

	/**
	 * 自動更新品種コード
	 */
	private String contractAutoItemCode;

	/**
	 * 契約年数
	 */
	private String contractYears;

	/**
	 * 数量
	 */
	private Integer quantity;
}
