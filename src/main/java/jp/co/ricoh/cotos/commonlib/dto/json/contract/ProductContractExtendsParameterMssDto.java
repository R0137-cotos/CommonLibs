package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 商品（契約用）拡張項目DTO（MSS）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractExtendsParameterMssDto {
	/**
	 * 契約種別詳細
	 */
	private String contractTypeDetails;

	/**
	 * 新規/既存アカウント区分
	 */
	private String newExistingAccountType;

	/**
	 * オーガニゼーションID
	 */
	private String organizationId;
}
