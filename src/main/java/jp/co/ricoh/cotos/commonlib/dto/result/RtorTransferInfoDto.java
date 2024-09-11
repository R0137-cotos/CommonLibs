package jp.co.ricoh.cotos.commonlib.dto.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.LifecycleStatus;
import lombok.Data;

/**
 * RtoR引継ぎ先情報DTO
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RtorTransferInfoDto {
	/**
	 * 引継ぎ先契約ID(rj管理番号)
	 */
	@ApiModelProperty(value = "引継ぎ先契約ID", required = false, position = 2, allowableValues = "range[0,255]")
	private String transferRjManageNumber;

	/**
	 * 契約ID
	 */
	@ApiModelProperty(value = "契約ID", required = true, position = 3)
	private long contractId;
	/**
	 * 契約状態
	 */
	@ApiModelProperty(value = "契約状態<br />" //
			+ "状態遷移上のライフサイクル状態を表す。", //
			required = false, allowableValues = "作成中(\"1\"), 作成完了(\"2\"), キャンセル手続き中(\"3\"), 破棄(\"4\"), 予定日待ち(\"5\"), 締結中(\"6\"), 解約手続き中(\"7\"), 解約予定日待ち(\"8\"), 解約(\"9\"), 旧契約(\"10\"), 締結待ち(\"11\")", position = 4) //
	private LifecycleStatus lifecycleStatus;

	/**
	 * 文書番号
	 */
	@ApiModelProperty(value = "文書番号", required = true, position = 5, allowableValues = "range[0,255]", readOnly = true)
	private String contractNumber;

	/**
	 * 文書番号枝番
	 */
	@ApiModelProperty(value = "文書番号枝番", required = true, position = 6, allowableValues = "range[0,99]", readOnly = true)
	private int contractBranchNumber;
}
