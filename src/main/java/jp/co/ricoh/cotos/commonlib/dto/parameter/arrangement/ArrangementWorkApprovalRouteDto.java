package jp.co.ricoh.cotos.commonlib.dto.parameter.arrangement;

import java.util.List;

import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ArrangementWorkApprovalRouteDto extends DtoBase {

	/**
	 * 承認依頼者MoM社員ID
	 */
	@Size(max = 255)
	@Schema(description = "承認依頼者MoM社員ID", required = true, allowableValues = "range[0,255]")
	private String approvalRequesterEmpId;

	/**
	 * 承認依頼者氏名
	 */
	@Size(max = 255)
	@Schema(description = "承認依頼者氏名", required = true, allowableValues = "range[0,255]")
	private String approvalRequesterName;

	/**
	 * 承認依頼者組織名
	 */
	@Size(max = 255)
	@Schema(description = "承認依頼者組織名", required = false, allowableValues = "range[0,255]")
	private String approvalRequesterOrgName;

	/**
	 * 承認ルートマスタID
	 */
	@Schema(description = "承認ルートマスタID", required = false)
	private Long approvalRouteMasterId;

	/**
	 * 手配業務承認ルートノード
	 */
	@NotNull
	@Valid
	@OneToMany(mappedBy = "arrangementWorkApprovalRoute")
	@Schema(description = "手配業務承認ルートノード", required = true)
	private List<ArrangementWorkApprovalRouteNodeDto> arrangementWorkApprovalRouteNodeList;
}
