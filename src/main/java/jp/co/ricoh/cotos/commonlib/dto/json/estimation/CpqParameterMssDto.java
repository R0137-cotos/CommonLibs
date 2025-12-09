package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeMss.NewExistingAccountTypeMss;
import lombok.Data;

/**
 * 商品（見積用）拡張項目COTOS商品固有項目DTO（MSS）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqParameterMssDto {

	/**
	 * 新規/既存アカウント区分
	 */
	private NewExistingAccountTypeMss newExistingAccountType;

	/**
	 * オーガニゼーションID
	 */
	private String organizationId;
}
