package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 商品（契約用）拡張項目McAfee商材固有項目検索条件DTO
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractMcAfeeSpecialDto {

	/**
	 *  McAfee商材固有項目検索条件
	 */
	@JsonProperty("mcAfeeSearchCondition")
	private ProductContractMcAfeeSearchConditionDto productContractMcAfeeSearchConditionDto;

	/**
	 * 情報変更表示フラグ
	 */
	private String infoChangeDisplayFlg;

	/**
	 * 情報変更スキップフラグ
	 */
	private String infoChangeSkipFlg;
}
