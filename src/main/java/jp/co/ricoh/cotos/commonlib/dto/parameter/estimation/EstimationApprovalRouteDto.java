package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class EstimationApprovalRouteDto extends DtoBase {

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
	 * 見積承認ルートノード
	 */
	@NotNull
	@Valid
	@Schema(description = "見積承認ルートノード", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<EstimationApprovalRouteNodeDto> estimationApprovalRouteNodeList;
}
