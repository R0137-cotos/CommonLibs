package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 商品（契約用）拡張項目DTO（DSIM）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractExtendsParameterDsimDto {

	/**
	 * 初回課金開始日
	 */
	private String firstBillingStartDate;

}
