package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.MigrationDiv;
import lombok.Data;

/**
 * 商品（契約用）拡張項目移行用項目DTO（標準）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductContractMigrationParameterDto {
	/**
	 * 移行区分
	 */
	private MigrationDiv migrationDiv;

	/**
	 * 初回契約課金開始日
	 */
	private String firstBillingStartDate;

	/**
	 * 基本契約課金開始日
	 */
	private String basicBillingStartDate;

	/**
	 * 基本契約サービス開始日
	 */
	private String basicServiceTermStart;

	/**
	 * 業務受付日
	 */
	private String operationAcceptanceDate;
}
