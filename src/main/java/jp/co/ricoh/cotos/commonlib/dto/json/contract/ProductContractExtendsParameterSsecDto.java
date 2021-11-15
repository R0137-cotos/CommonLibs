package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.NewExistingAccountType;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.OnsiteFlg;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeSsec.IntActingDivSsec;
import lombok.Data;

/**
 * 商品（契約用）拡張項目DTO（SSec）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractExtendsParameterSsecDto {
	/**
	 * 契約種別詳細
	 */
	private ContractTypeDetails contractTypeDetails;

	/**
	 * オンサイト有無フラグ
	 */
	private OnsiteFlg onSiteFlg;

	/**
	 * 導入代行区分
	 */
	private IntActingDivSsec intActingDiv;

	/**
	 * 新規/既存アカウント区分
	 */
	private NewExistingAccountType newExistingAccountType;

	/**
	 * DSaaSアカウント
	 */
	private String dsaasAccount;
}
