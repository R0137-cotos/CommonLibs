package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ商品固有戻り値DTO（RNS）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqReturnValueRnsDto {

	/**
	 * 見積種別詳細
	 */
	private String estimationTypeDetails;
}
