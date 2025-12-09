package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 商品（見積用）拡張項目DTO（CGWP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductEstimationExtendsParameterCgwpDto {

	/**
	 * CPQ商品固有戻り値
	 */
	@JsonProperty("cpqProductSpecificReturnValue")
	private CpqReturnValueCgwpDto cpqReturnValueCgwpDto;

	/**
	 * COTOS商品固有項目
	 */
	@JsonProperty("cotosProductSpecificParameter")
	private CotosParameterCgwpDto cotosParameterCgwpDto;
}
