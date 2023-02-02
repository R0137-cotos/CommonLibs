package jp.co.ricoh.cotos.commonlib.dto.json.externalLinkage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 商材固有項目用拡張項目DTO（メールアドレス追加詳細）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecificControlExtendsParameterMailAddDto {

	/**
	 * メールアドレス追加詳細リスト
	 */
	@JsonProperty("mailAddMailRequestList")
	private List<SpecificControlMailAddDto> specificControlMailAddDtoList;
}
