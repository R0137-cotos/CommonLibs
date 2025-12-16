package jp.co.ricoh.cotos.commonlib.entity.contract;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.master.ApprovalRouteNodeMaster.ApproverDeriveMethodDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約承認を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "contract_approval_route_node")
public class ContractApprovalRouteNode extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_approval_route_node_seq")
	@SequenceGenerator(name = "contract_approval_route_node_seq", sequenceName = "contract_approval_route_node_seq", allocationSize = 1)
	@Schema(description = "契約承認ルートノードID(作成時不要)", required = true, allowableValues = "range[0,9999999999999999999]", readOnly = true)
	private long id;

	/**
	 * 契約承認ルート
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "contract_approval_route_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "契約承認ルート", requiredMode = Schema.RequiredMode.REQUIRED)
	private ContractApprovalRoute contractApprovalRoute;

	/**
	 * 承認順
	 */
	@Column(nullable = false)
	@OrderBy("desc")
	@Max(999)
	@Min(0)
	@Schema(description = "承認順", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,999]")
	private int approvalOrder;

	/**
	 * 承認者組織階層レベル
	 */
	@Min(0)
	@Schema(description = "承認者組織階層レベル", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,2147483647]")
	private Integer approverOrgLevel;

	/**
	 * 承認者MoM社員ID
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "承認者MoM社員ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String approverEmpId;

	/**
	 * 承認者氏名
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "承認者氏名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String approverName;

	/**
	 * 承認者組織名
	 */
	@Size(max = 255)
	@Schema(description = "承認者組織名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String approverOrgName;

	/**
	 * 代理承認者MoM社員ID
	 */
	@Size(max = 255)
	@Schema(description = "代理承認者MoM社員ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String subApproverEmpId;

	/**
	 * 代理承認者氏名
	 */
	@Size(max = 255)
	@Schema(description = "代理承認者氏名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String subApproverName;

	/**
	 * 代理承認者組織名
	 */
	@Size(max = 255)
	@Schema(description = "代理承認者組織名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String subApproverOrgName;

	/**
	 * 承認者導出方式区分
	 */
	@Schema(description = "承認者導出方式区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "直属上司指定(\"1\"), 組織絶対階層指定(\"2\"), -組織直接指定(\"3\"), ユーザー直接指定(\"4\"), 自己承認(\"5\"), 受付担当CE指定(\"6\"), グループ承認(\"7\")", example = "1")
	private ApproverDeriveMethodDiv approverDeriveMethodDiv;

	/**
	 * グループ名
	 */
	@Size(max = 255)
	@Schema(description = "グループ名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String groupName;
}
