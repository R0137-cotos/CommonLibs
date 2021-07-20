package jp.co.ricoh.cotos.commonlib.dto.parameter.common;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 日付計算パターンマスタを表すDto
 */
@Data
public class DateCalcPatternMasterDto {

	/**
	 * 拡張項目DTOリスト
	 */
	@JsonProperty("dateCalcPattern")
	private List<DateCalcPatternMasterExtendsParameterDto> dateCalcPatternMasterExtendsParameterDtoList;
}
