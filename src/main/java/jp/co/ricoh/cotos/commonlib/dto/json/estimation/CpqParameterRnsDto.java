package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 商品（見積用）拡張項目COTOS商品固有項目DTO（RNS）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqParameterRnsDto {

	/**
	 * 新規/既存アカウント区分
	 */
	private String newExistingAccountType;

	/**
	 * オーガニゼーションID
	 */
	private String organizationId;
}
