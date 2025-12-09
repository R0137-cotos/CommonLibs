package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class O365LatestContractParameter {

	/**
	 * RJ管理番号
	 */
	@Schema(description = "RJ管理番号", required = true)
	String rjManageNumber;

	/**
	 * サブドメイン名(MSアカウント)
	 */
	@Schema(description = "サブドメイン名(MSアカウント)", required = true)
	String subDomainName;
}
