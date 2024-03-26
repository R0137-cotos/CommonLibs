package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeMits.BillingMethod;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeMits.ContractYears;
import lombok.Data;

/**
 * 商品（契約用）拡張項目DTO（MITS）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractExtendsParameterMitsDto {
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
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	private Date conclusionPreferredDate;
}
