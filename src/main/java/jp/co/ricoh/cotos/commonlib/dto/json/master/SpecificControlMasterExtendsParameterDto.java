package jp.co.ricoh.cotos.commonlib.dto.json.master;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 商材固有項目用マスタ拡張項目DTO
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecificControlMasterExtendsParameterDto {

	/**
	 * 必須
	 */
	private Integer required;

	/**
	 * 最小文字数
	 */
	private Integer minLength;

	/**
	 * 最大文字数
	 */
	private Integer maxLength;

	/**
	 * 最小byte数
	 */
	private Integer minByte;

	/**
	 * 最大byte数
	 */
	private Integer maxByte;

	/**
	 * 正規表現
	 */
	private String formatRegex;

	/**
	 * 特殊パターン用
	 */
	private Integer formatSpecial;
}
