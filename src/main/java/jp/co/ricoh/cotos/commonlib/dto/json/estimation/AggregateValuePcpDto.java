package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.AggregateValueDeleteFlgPcp;
import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ連携集計値リストDTO（PCP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AggregateValuePcpDto {

	/**
	 * CPQマッピングキー
	 */
	private String cpqMappingKey;

	/**
	 * 数量
	 */
	private Integer quantity;

	/**
	 * 削除フラグ
	 */
	private AggregateValueDeleteFlgPcp deleteFlg;
}
