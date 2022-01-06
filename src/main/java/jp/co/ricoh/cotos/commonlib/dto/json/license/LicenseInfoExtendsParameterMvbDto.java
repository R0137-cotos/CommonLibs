package jp.co.ricoh.cotos.commonlib.dto.json.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * ライセンス情報拡張項目DTO（MVB）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseInfoExtendsParameterMvbDto {

	/**
	 * インストールURL
	 */
	private String installUrl;
}
