package jp.co.ricoh.cotos.commonlib.dto.json.license;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * ライセンスキー情報拡張項目DTO（MVB）
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenseKeyInfoExtendsParameterMvbDto {

	/**
	 * インストールURL
	 */
	private String installUrl;
}
