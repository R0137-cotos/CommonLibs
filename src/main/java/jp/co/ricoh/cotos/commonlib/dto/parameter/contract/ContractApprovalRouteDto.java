package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import java.util.List;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.LifecycleStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ContractApprovalRouteDto extends DtoBase {

	/**
	 * 対象ライフサイクル状態
	 */
	@NotNull
	@Schema(description = "対象ライフサイクル状態", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "作成中(\"1\"), 作成完了(\"2\"), キャンセル手続き中(\"3\"), 破棄(\"4\"), 予定日待ち(\"5\"), 締結中(\"6\"), 解約手続き中(\"7\"), 解約予定日待ち(\"8\"), 解約(\"9\"), 旧契約(\"10\")", example = "1")
	private LifecycleStatus targetLifecycleStatus;

	/**
	 * 承認依頼者MoM社員ID
	 */
	@Size(max = 255)
	@Schema(description = "承認依頼者MoM社員ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String approvalRequesterEmpId;

	/**
	 * 承認依頼者氏名
	 */
	@Size(max = 255)
	@Schema(description = "承認依頼者氏名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String approvalRequesterName;

	/**
	 * 承認依頼者組織名
	 */
	@Size(max = 255)
	@Schema(description = "承認依頼者組織名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String approvalRequesterOrgName;

	/**
	 * 特価承認対象フラグ
	 */
	@Min(0)
	@Max(9)
	@Schema(description = "特価承認対象フラグ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9]")
	private int specialPriceApprovalFlg;

	/**
	 * 承認ルートマスタID
	 */
	@Schema(description = "承認ルートマスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long approvalRouteMasterId;

	/**
	 * 契約承認ルートノード
	 */
	@NotNull
	@Valid
	@OneToMany(mappedBy = "contractApprovalRoute")
	@Schema(description = "契約承認ルートノード", requiredMode = Schema.RequiredMode.REQUIRED)
	@OrderBy("approvalOrder ASC")
	private List<ContractApprovalRouteNodeDto> contractApprovalRouteNodeList;
}
