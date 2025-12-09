package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * RSI顧客テナント情報検索用パラメータ
 */
@Data
public class RsiFindCustomerTenantParameter {

	/**
	 * テナントID
	 */
	@Parameter(description = "テナントID", required = false)
	@Schema(description = "テナントID", required = false, allowableValues = "range[0,255]")
	private String tenantId;

	/**
	 * 契約者メールアドレス
	 */
	@Parameter(description = "契約者メールアドレス", required = false)
	@Schema(description = "契約者メールアドレス", required = false, allowableValues = "range[0,255]")
	private String contractorMailAddress;
}
