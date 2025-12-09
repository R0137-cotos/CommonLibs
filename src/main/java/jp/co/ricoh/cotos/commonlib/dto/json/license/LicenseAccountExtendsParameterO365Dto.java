package jp.co.ricoh.cotos.commonlib.dto.json.license;

import lombok.Data;

/**
 * ライセンスアカウント拡張項目DTO（O365）
 */
@Data
public class LicenseAccountExtendsParameterO365Dto {

	/**
	 * 希望サブドメイン名
	 */
	private String requestSubDomainName;

	/**
	 * サブドメイン名
	 */
	private String subDomainName;
}
