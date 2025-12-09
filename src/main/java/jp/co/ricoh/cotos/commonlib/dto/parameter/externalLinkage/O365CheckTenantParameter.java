package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * O365テナント存在確認用パラメータ
 */
@Data
public class O365CheckTenantParameter {

	/**
	 * 見積番号
	 */
	@Schema(description = "見積番号", required = true, allowableValues = "range[0,255]")
	private String estimationNumber;

	/**
	 * 企事部ID
	 */
	@Schema(description = "企事部ID", required = true, allowableValues = "range[0,255]")
	private String momCustId;

	/**
	 * テナントID(ディレクトリID)
	 */
	@Schema(description = "テナントID(ディレクトリID)", required = true, allowableValues = "range[0,255]")
	private String tenantId;

	/**
	 * ドメイン名
	 */
	@Schema(description = "ドメイン名", required = true, allowableValues = "range[0,255]")
	private String domainName;

	/**
	 * プライマリドメイン名
	 */
	@Schema(description = "プライマリドメイン名", required = true, allowableValues = "range[0,255]")
	private String primaryDomainName;
}
