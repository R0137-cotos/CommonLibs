package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeMits.BillingMethod;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeMits.ContractYears;
import jp.co.ricoh.cotos.commonlib.dto.json.estimation.ProductEstimationMigrationParameterDto;
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
	@NotNull
	private ContractTypeDetails contractTypeDetails;

	/**
	 * 請求方法
	 */
	@NotNull
	private BillingMethod billingMethod;

	/**
	 * 契約年数
	 */
	private ContractYears contractYears;

	/**
	 * サービス利用希望日
	 */
	private boolean conclusionPreferredDate;

	/**
	 * 移行用項目
	 */
	@JsonProperty("migrationParameter")
	private ProductEstimationMigrationParameterDto productEstimationMigrationParameterDto;
}
