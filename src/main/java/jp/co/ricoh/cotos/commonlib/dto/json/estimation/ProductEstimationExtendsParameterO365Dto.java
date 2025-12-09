package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 商品（見積用）拡張項目DTO（O365）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductEstimationExtendsParameterO365Dto {

	/**
	 * 希望サブドメイン名
	 */
	private String desiredSubDomainName;

	/**
	 * CPQ商品固有戻り値
	 */
	@JsonProperty("cpqProductSpecificReturnValue")
	private CpqReturnValueO365Dto cpqProductSpecificReturnValueDto;

	/**
	 * 品種(見積用)
	 */
	@JsonProperty("itemEstimation")
	private List<ItemEstimationO365Dto> itemEstimationDtoList;

}
