package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 商品（見積用）拡張項目COTOS商品固有項目DTO（PCP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CotosProductSpecificParameterDto {

	/**
	 * COTOS:見積機種リスト
	 */
	@JsonProperty("cotosEstimationEquipment")
	private List<ParameterEstimationEquipmentPcpDto> parameterEstimationEquipmentPcpDtoList;
}
