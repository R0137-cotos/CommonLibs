package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * RSI顧客テナント情報検索用パラメータ
 */
@Data
public class RsiFindCustomerTenantParameter {

	/**
	 * テナントID
	 */
	@ApiParam(value = "テナントID", required = false)
	@ApiModelProperty(value = "テナントID", required = false, allowableValues = "range[0,255]")
	private String tenantId;

	/**
	 * 契約者メールアドレス
	 */
	@ApiParam(value = "契約者メールアドレス", required = false)
	@ApiModelProperty(value = "契約者メールアドレス", required = false, allowableValues = "range[0,255]")
	private String contractorMailAddress;
}
