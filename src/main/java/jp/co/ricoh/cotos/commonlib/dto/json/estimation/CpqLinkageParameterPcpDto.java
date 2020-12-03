package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ連携集計値DTO（PCP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqLinkageParameterPcpDto {
	/**
	 * 集計値リスト
	 */
	@JsonProperty("aggregateValue")
	private List<AggregateValuePcpDto> aggregateValuePcpDtoList;
}
