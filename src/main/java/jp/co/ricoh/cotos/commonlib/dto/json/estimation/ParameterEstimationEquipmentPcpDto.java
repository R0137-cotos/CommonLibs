package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * 商品（見積用）拡張項目COTOS商品固有項目 見積機種リストDTO（PCP）
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParameterEstimationEquipmentPcpDto {
	/**
	 * 継続可能フラグ
	 */
	private String continuePossibleFlg;

	/**
	 * 再契約不可フラグ
	 */
	private String reContractNotAllowedFlg;

	/**
	 * 機種コード
	 */
	private String equipmentCode;

	/**
	 * 機種名
	 */
	private String equipmentName;

	/**
	 * 機番
	 */
	private String equipmentNumber;

	/**
	 * 型番
	 */
	private String modelNumber;

	/**
	 * 枝番
	 */
	private String branchesNumber;

	/**
	 * メーカー名
	 */
	private String makerName;

	/**
	 * 購入日
	 */
	private Date purchaseDate;

	/**
	 * 保守終了日
	 */
	private Date maintenanceEndDate;

	/**
	 * 本体フラグ
	 */
	private String bodyFlg;

	/**
	 * サービス機器フラグ
	 */
	private String serviceMachineFlg;

	/**
	 * CPQマッピングキー
	 */
	private String cpqMappingKey;

	/**
	 * 数量
	 */
	private Integer quantity;

	/**
	 * 削除フラグ
	 */
	private String deleteFlg;
}