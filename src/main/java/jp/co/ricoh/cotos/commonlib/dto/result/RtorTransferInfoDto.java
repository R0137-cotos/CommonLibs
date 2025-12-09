package jp.co.ricoh.cotos.commonlib.dto.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "引継ぎ先契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String transferRjManageNumber;

	/**
	 * 契約ID
	 */
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.REQUIRED)
	private long contractId;
	/**
	 * 契約状態
	 */
	@Schema(description = "契約状態<br />" //
			+ "状態遷移上のライフサイクル状態を表す。", //
			required = false, allowableValues = "作成中(\"1\"), 作成完了(\"2\"), キャンセル手続き中(\"3\"), 破棄(\"4\"), 予定日待ち(\"5\"), 締結中(\"6\"), 解約手続き中(\"7\"), 解約予定日待ち(\"8\"), 解約(\"9\"), 旧契約(\"10\"), 締結待ち(\"11\")") //
	private LifecycleStatus lifecycleStatus;

	/**
	 * 文書番号
	 */
	@Schema(description = "文書番号", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]", readOnly = true)
	private String contractNumber;

	/**
	 * 文書番号枝番
	 */
	@Schema(description = "文書番号枝番", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,99]", readOnly = true)
	private int contractBranchNumber;
}
