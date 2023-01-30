package jp.co.ricoh.cotos.commonlib.dto.json.master;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 商材固有項目用マスタ拡張項目DTO
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecificControlMasterExtendsParameterDto {

	/**
	 * 入力チェック定義
	 */
	@JsonProperty("inputCheck")
	private InputCheckDto inputCheckDto;
}
