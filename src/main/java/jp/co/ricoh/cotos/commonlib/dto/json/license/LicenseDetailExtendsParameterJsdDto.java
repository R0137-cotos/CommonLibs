package jp.co.ricoh.cotos.commonlib.dto.json.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * ライセンス明細拡張項目DTO（JSD）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseDetailExtendsParameterJsdDto {

	/**
	 * 変更前数量
	 */
	private String beforeQuantity;
}
