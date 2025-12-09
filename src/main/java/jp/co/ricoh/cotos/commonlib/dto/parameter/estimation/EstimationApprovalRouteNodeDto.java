package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.master.ApprovalRouteNodeMaster.ApproverDeriveMethodDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class EstimationApprovalRouteNodeDto extends DtoBase {

	/**
	 * 承認順
	 */
	@Min(0)
	@Max(999)
	@Schema(description = "承認順", required = true, allowableValues = "range[0,999]")
	private int approvalOrder;

	/**
	 * 承認者組織階層レベル
	 */
	@Min(0)
	@Max(9)
	@Schema(description = "承認者組織階層レベル", required = false, allowableValues = "range[0,9]")
	private Integer approverOrgLevel;

	/**
	 * 承認者MoM社員ID
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "承認者MoM社員ID", required = true, allowableValues = "range[0,255]")
	private String approverEmpId;

	/**
	 * 承認者氏名
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "承認者氏名", required = true, allowableValues = "range[0,255]")
	private String approverName;

	/**
	 * 承認者組織名
	 */
	@Size(max = 255)
	@Schema(description = "承認者組織名", required = false, allowableValues = "range[0,255]")
	private String approverOrgName;

	/**
	 * 代理承認者MoM社員ID
	 */
	@Size(max = 255)
	@Schema(description = "代理承認者MoM社員ID", required = false, allowableValues = "range[0,255]")
	private String subApproverEmpId;

	/**
	 * 代理承認者氏名
	 */
	@Size(max = 255)
	@Schema(description = "代理承認者氏名", required = false, allowableValues = "range[0,255]")
	private String subApproverName;

	/**
	 * 代理承認者組織名
	 */
	@Size(max = 255)
	@Schema(description = "代理承認者組織名", required = false, allowableValues = "range[0,255]")
	private String subApproverOrgName;

	/**
	 * 承認者導出方式区分
	 */
	@Schema(description = "承認者導出方式区分", required = false, allowableValues = "直属上司指定(\"1\"), 組織絶対階層指定(\"2\"), 組織直接指定(\"3\"), ユーザー直接指定(\"4\"), 自己承認(\"5\"), 受付担当CE指定(\"6\"), グループ承認(\"7\")\"", example = "1")
	private ApproverDeriveMethodDiv approverDeriveMethodDiv;

	/**
	 * グループ名
	 */
	@Size(max = 255)
	@Schema(description = "グループ名", required = false, allowableValues = "range[0,255]")
	private String groupName;
}
