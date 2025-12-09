package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 商品（見積用）拡張項目DTO（DSIM）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductEstimationExtendsParameterDsimDto {

	/**
	 * 初回課金開始日
	 */
	private String firstBillingStartDate;

}
