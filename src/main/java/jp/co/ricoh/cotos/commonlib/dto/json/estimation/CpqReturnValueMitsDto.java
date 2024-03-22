package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeMits.BillingMethod;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeMits.ContractYears;
import lombok.Data;

/**
 * 商品（見積用）拡張項目CPQ商品固有戻り値DTO（MITS）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CpqReturnValueMitsDto {

	/**
	 * 契約種別詳細
	 */
	private ContractTypeDetails contractTypeDetails;

	/**
	 * 請求方法
	 */
	private BillingMethod billingMethod;

	/**
	 * 契約年数
	 */
	private ContractYears contractYears;

	/**
	 * サービス利用希望日
	 */
	private boolean conclusionPreferredDate;

}