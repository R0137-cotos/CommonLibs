package jp.co.ricoh.cotos.commonlib.dto.json.estimation;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.ClassificationCodePcp;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.ContinuePossibleFlgPcp;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.ParameterEstimationEquipmentDeleteFlgPcp;
import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.ReContractNotAllowedFlgPcp;
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
	private ContinuePossibleFlgPcp continuePossibleFlg;

	/**
	 * 再契約不可フラグ
	 */
	private ReContractNotAllowedFlgPcp reContractNotAllowedFlg;

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
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
	private Date purchaseDate;

	/**
	 * 保守終了日
	 */
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Tokyo")
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
	 * 数量
	 */
	private Integer quantity;

	/**
	 * 削除フラグ
	 */
	private ParameterEstimationEquipmentDeleteFlgPcp deleteFlg;

	/**
	 * 品種コード
	 */
	private String itemCode;

	/**
	 * 変更元契約機種ID
	 */
	private Long originContractEquipmentId;

	/**
	 * 分類コード
	 */
	private ClassificationCodePcp classificationCode;

	/**
	 * 分類名
	 */
	private String classificationName;

	/**
	 * 保守可能年数
	 */
	private String maintenanceYears;
}