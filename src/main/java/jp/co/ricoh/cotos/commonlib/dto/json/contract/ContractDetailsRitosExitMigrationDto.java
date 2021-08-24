package jp.co.ricoh.cotos.commonlib.dto.json.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 契約明細拡張項目RITOS移行用DTO（脱RITOS商品）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractDetailsRitosExitMigrationDto {
	/**
	 * RITOS文書番号
	 */
	private String ritosDocumentNumber;

	/**
	 * 基本契約通し番号
	 */
	private Integer basicContractSerialNumber;

	/**
	 * RITOS提供サービス番号
	 */
	private String npServiceNo;

	/**
	 * RITOS検収日
	 */
	private String ritosAcceptanceDate;

	/**
	 * RITOS契約開始日
	 */
	private String ritosFromDate;

	/**
	 * RITOS確定納期
	 */
	private String ritosFixDueDate;
}
