package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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

	/**
	 * 品種(契約用)
	 */
	@JsonProperty("itemContract")
	private List<ItemContractO365Dto> itemContractDtoList;

	/**
	 * キャンセル申込日
	 */
	private String cancellationApplicationDate;

	/**
	 * キャンセル可能期日
	 */
	private String cancellationDate;
}
