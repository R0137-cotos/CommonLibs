package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class O365LatestContractParameter {

	/**
	 * RJ管理番号
	 */
	@ApiModelProperty(value = "RJ管理番号", required = true, position = 1)
	String rjManageNumber;

	/**
	 * サブドメイン名(MSアカウント)
	 */
	@ApiModelProperty(value = "サブドメイン名(MSアカウント)", required = true, position = 2)
	String subDomainName;
}
