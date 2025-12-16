package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangement;

import java.util.Date;
import java.util.List;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWork.WorkflowStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ArrangementWorkDto extends DtoBase {

	/**
	 * 手配
	 */
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	@JoinColumn(name = "arrangement_id", referencedColumnName = "id")
	@Schema(description = "手配", requiredMode = Schema.RequiredMode.REQUIRED)
	private ArrangementDto arrangement;

	/**
	 * 手配業務タイプマスタID
	 */
	@Schema(description = "手配業務タイプマスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long arrangementWorkTypeMasterId;

	/**
	 * ワークフロー状態
	 */
	@NotNull
	@Schema(description = "ワークフロー状態", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "受付待ち(\"1\"), 作業中(\"2\"), 作業完了報告(\"3\"), 承認依頼中(\"4\"), 作業完了(\"5\"), エラー(\"6\"), 破棄(\"7\")", example = "1")
	private WorkflowStatus workflowStatus;

	/**
	 * メモ
	 */
	@Size(max = 4000)
	@Schema(description = "メモ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,4000]")
	private String memo;

	/**
	 * 保留フラグ
	 */
	@Min(0)
	@Max(9)
	@Schema(description = "保留フラグ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9]")
	private int holdingFlg;

	/**
	 * 手配業務承認ルート
	 */
	@Valid
	@OneToOne(mappedBy = "arrangementWork")
	@Schema(description = "手配業務承認ルート", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ArrangementWorkApprovalRouteDto arrangementWorkApprovalRoute;

	/**
	 * 担当作業者社員
	 */
	@Valid
	@OneToOne(mappedBy = "arrangementWork")
	@Schema(description = "担当作業者社員", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ArrangementPicWorkerEmpDto arrangementPicWorkerEmp;

	/**
	 * 手配業務添付ファイル
	 */
	@Valid
	@OneToMany(mappedBy = "arrangementWork")
	@Schema(description = "手配業務添付ファイル", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ArrangementWorkAttachedFileDto> workAttachedFileList;

	/**
	 * 手配業務チェック結果
	 */
	@Valid
	@OneToMany(mappedBy = "arrangementWork")
	@Schema(description = "手配業務チェック結果", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ArrangementWorkCheckResultDto> arrangementWorkCheckResultList;

	/**
	 * 作業完了日時
	 */
	@Schema(description = "作業完了日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.TIMESTAMP)
	private Date workCompletedAt;

	/**
	 * 業務受理日時
	 */
	@Schema(description = "業務受理日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.TIMESTAMP)
	private Date businessAcceptanceDateTime;
}
