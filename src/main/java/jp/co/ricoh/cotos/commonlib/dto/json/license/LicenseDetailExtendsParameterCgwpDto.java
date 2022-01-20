package jp.co.ricoh.cotos.commonlib.dto.json.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * ライセンス明細拡張項目DTO（CGWP）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseDetailExtendsParameterCgwpDto {

	/**
	 * 変更前数量
	 */
	private String beforeQuantity;
}
