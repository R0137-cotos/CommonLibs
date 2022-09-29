package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * O365テナント存在確認用パラメータ
 */
@Data
public class O365CheckTenantParameter {

	/**
	 * 見積番号
	 */
	@ApiModelProperty(value = "見積番号", required = true, allowableValues = "range[0,255]", position = 1)
	private String estimationNumber;

	/**
	 * 企事部ID
	 */
	@ApiModelProperty(value = "企事部ID", required = true, allowableValues = "range[0,255]", position = 2)
	private String momCustId;

	/**
	 * テナントID(ディレクトリID)
	 */
	@ApiModelProperty(value = "テナントID(ディレクトリID)", required = true, allowableValues = "range[0,255]", position = 3)
	private String tenantId;

	/**
	 * ドメイン名
	 */
	@ApiModelProperty(value = "ドメイン名", required = true, allowableValues = "range[0,255]", position = 4)
	private String domainName;

	/**
	 * プライマリドメイン名
	 */
	@ApiModelProperty(value = "プライマリドメイン名", required = true, allowableValues = "range[0,255]", position = 5)
	private String primaryDomainName;
}
