package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.EstimationTypeDetails;
import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ商品固有戻り値DTO（PCP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqReturnValuePcpDto {

	/**
	 * 見積種別詳細
	 */
	private EstimationTypeDetails estimationTypeDetails;

	/**
	 * 見積機種リスト
	 */
	@JsonProperty("estimationEquipment")
	private List<CpqEstimationEquipmentPcpDto> productEstimationCpqEstimationEquipmentPcpDtoList;
}
