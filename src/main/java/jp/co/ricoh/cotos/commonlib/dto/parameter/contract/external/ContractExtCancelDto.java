package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.external;

import java.util.Date;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.LifecycleStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.WorkflowStatus;
import lombok.Data;

@Data
public class ContractExtCancelDto {

	/**
	 * 契約ID
	 */
	@Min(0)
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@NotNull
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * ライフサイクル状態
	 */
	@NotNull
	@Schema(description = "ライフサイクル状態", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "作成中(\"1\"), 作成完了(\"2\"), キャンセル手続き中(\"3\"), 破棄(\"4\"), 予定日待ち(\"5\"), 締結中(\"6\"), 解約手続き中(\"7\"), 解約予定日待ち(\"8\"), 解約(\"9\"), 旧契約(\"10\")", example = "1")
	private LifecycleStatus lifecycleStatus;

	/**
	 * ワークフロー状態
	 */
	@NotNull
	@Schema(description = "ワークフロー状態", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "作成中(\"1\"), 承認依頼中(\"2\"), 承認済(\"3\"), 業務依頼中(\"4\"), 業務処理完了(\"5\"), キャンセル申請中(\"6\"), 売上可能(\"7\"), 解約申請中(\"8\")", example = "1")
	private WorkflowStatus workflowStatus;

	/**
	 * 解約予定日
	 */
	@Schema(description = "解約予定日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date cancelScheduledDate;

	/**
	 * 解約理由
	 */
	@Size(max = 255)
	@Schema(description = "解約理由", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cancelReason;

	/**
	 * その他解約理由
	 */
	@Size(max = 1000)
	@Schema(description = "その他解約理由", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1000]")
	private String cancelReasonEtc;

	/**
	 * 解約注文番号
	 */
	@Size(max = 255)
	@Schema(description = "解約注文番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cancelOrderNo;

	/**
	 * 解約申込日
	 */
	@Schema(description = "解約申込日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date cancelApplicationDate;

	/**
	 * 違約金用FFM発注問合せ番号
	 */
	@Size(max = 255)
	@Schema(description = "違約金用FFM発注問合せ番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String penaltyFfmOrderContactNo;

}
