package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.NewExistingAccountType;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.OnsiteFlg;
import lombok.Data;

/**
 * 商品（契約用）拡張項目DTO（MVB）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractExtendsParameterMvbDto {
	/**
	 * 契約種別詳細
	 */
	private ContractTypeDetails contractTypeDetails;

	/**
	 * オンサイト有無フラグ
	 */
	private OnsiteFlg onSiteFlg;

	/**
	 * 新規/既存アカウント区分
	 */
	private NewExistingAccountType newExistingAccountType;

	/**
	 * MVBアカウント
	 */
	private String mvbAccount;

	/**
	 * インストールURL
	 */
	private String installUrl;
}
