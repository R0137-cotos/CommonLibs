package jp.co.ricoh.cotos.commonlib.dto.json.license;

import lombok.Data;

/**
 * ライセンスアカウント拡張項目DTO（CAS、MVB）
 */
@Data
public class LicenseAccountExtendsParameterCasMvbDto {

	/**
	 * インストールURL
	 */
	private String installUrl;

	/**
	 * 有償サンドボックスサブスクリプションID
	 */
	private String paidSandboxSubscriptionId;
}
