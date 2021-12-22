package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import lombok.Data;

/**
 * 商品（契約用）拡張項目DTO（O365）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractExtendsParameterO365Dto {
	/**
	 * 契約種別詳細
	 */
	private ContractTypeDetails contractTypeDetails;

	/**
	 * 希望サブドメイン名
	 */
	private String desiredSubDomainName;

	/**
	 * ディレクトリID
	 */
	private String directoryId;

	/**
	 * サブドメイン名
	 */
	private String subDomainName;

	/**
	 * ドメイン名
	 */
	private String domainName;

	/**
	 * プライマリドメイン名
	 */
	private String primaryDomainName;
}
