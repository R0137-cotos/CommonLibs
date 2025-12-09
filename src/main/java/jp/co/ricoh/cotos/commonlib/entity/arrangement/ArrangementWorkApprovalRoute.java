package jp.co.ricoh.cotos.commonlib.entity.arrangement;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 手配業務に設定される承認ルートを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "arrangement_work_approval_route")
@Schema(description = "手配業務承認ルート(作成時不要)")
public class ArrangementWorkApprovalRoute extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arrangement_work_approval_route_seq")
	@SequenceGenerator(name = "arrangement_work_approval_route_seq", sequenceName = "arrangement_work_approval_route_seq", allocationSize = 1)
	@Schema(description = "手配業務承認ルートID (作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 手配業務
	 */
	@OneToOne(optional = false)
	@JsonIgnore
	@JoinColumn(name = "arrangement_work_id", referencedColumnName = "id")
	@Schema(description = "手配業務", required = true)
	private ArrangementWork arrangementWork;

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
	 * 手配業務承認実績
	 */
	@OneToMany(mappedBy = "arrangementWorkApprovalRoute")
	@OrderBy("processedAt ASC")
	@Schema(description = "手配業務承認実績", required = false)
	private List<ArrangementWorkApprovalResult> arrangementWorkApprovalResultList;

	/**
	 * 手配業務承認ルートノード
	 */
	@OneToMany(mappedBy = "arrangementWorkApprovalRoute")
	@OrderBy("approvalOrder ASC")
	@Schema(description = "手配業務承認ルートノード", required = true)
	private List<ArrangementWorkApprovalRouteNode> arrangementWorkApprovalRouteNodeList;

}
