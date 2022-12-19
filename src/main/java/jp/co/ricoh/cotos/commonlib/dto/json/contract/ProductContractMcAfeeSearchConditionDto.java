package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 商品（契約用）拡張項目McAfee固有項目DTO
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractMcAfeeSearchConditionDto {

	/**
	 * McAfee商材固有項目検索条件メールアドレス
	 */
	private String mcAfeeSearchConditionMail;

	/**
	 * McAfee商材固有項目検索条件顧客ID
	 */
	private String mcAfeeSearchConditionCustmerId;

	/**
	 * McAfee商材固有項目検索条件顧客名
	 */
	private String mcAfeeSearchConditionCustmerName;

}
