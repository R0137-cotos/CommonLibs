package jp.co.ricoh.cotos.commonlib.entity.estimation;

import java.util.List;

import jakarta.persistence.Column;
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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 見積承認ルートを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "estimation_approval_route")
@Schema(description = "見積承認ルート(作成時不要)")
public class EstimationApprovalRoute extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estimation_approval_route_seq")
	@SequenceGenerator(name = "estimation_approval_route_seq", sequenceName = "estimation_approval_route_seq", allocationSize = 1)
	@Schema(description = "見積承認ルートID(作成時不要)", required = true, allowableValues = "range[0,9999999999999999999]", readOnly = true)
	private long id;

	/**
	 * 見積
	 */
	@OneToOne
	@Schema(description = "見積", required = true)
	@JoinColumn(name = "estimation_id", referencedColumnName = "id")
	@JsonIgnore
	private Estimation estimation;

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
	 * 特価承認対象フラグ
	 */
	@Column(nullable = false)
	@Max(9)
	@Min(0)
	@Schema(description = "特価承認対象フラグ", required = true, allowableValues = "range[0,9]")
	private int specialPriceApprovalFlg;

	/**
	 * 承認ルートマスタID
	 */
	@Schema(description = "承認ルートマスタID", required = false)
	private Long approvalRouteMasterId;

	/**
	 * 見積承認実績
	 */
	@OneToMany(mappedBy = "estimationApprovalRoute")
	@OrderBy("processedAt ASC")
	@Schema(description = "見積承認実績(作成時不要)", required = false, readOnly = true)
	private List<EstimationApprovalResult> estimationApprovalResultList;

	/**
	 * 見積承認ルートノード
	 */
	@OneToMany(mappedBy = "estimationApprovalRoute")
	@OrderBy("approvalOrder ASC")
	@Schema(description = "見積承認ルートノード(作成時不要)", required = true, readOnly = true)
	private List<EstimationApprovalRouteNode> estimationApprovalRouteNodeList;

}
