package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 商品（見積用）拡張項目共通商品固有項目DTO（PCP）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonProductSpecificParameterPcpDto {

	/**
	 * 更新対象リスト
	 */
	@JsonProperty("updateTarget")
	List<UpdateTargetPcpDto> updateTargetPcpDtoList;
}
