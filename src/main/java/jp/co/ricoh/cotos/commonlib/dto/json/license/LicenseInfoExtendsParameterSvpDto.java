package jp.co.ricoh.cotos.commonlib.dto.json.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * ライセンス情報拡張項目DTO（SVP）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseInfoExtendsParameterSvpDto {

	/**
	 * CE連絡日時
	 */
	private String ceContactAt;
}
