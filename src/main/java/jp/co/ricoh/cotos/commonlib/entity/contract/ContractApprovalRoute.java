package jp.co.ricoh.cotos.commonlib.entity.contract;

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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.LifecycleStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約承認ルートを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "contract_approval_route")
public class ContractApprovalRoute extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_approval_route_seq")
	@SequenceGenerator(name = "contract_approval_route_seq", sequenceName = "contract_approval_route_seq", allocationSize = 1)
	@Schema(description = "契約承認ルートID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約
	 */
	@OneToOne(optional = false)
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "契約", requiredMode = Schema.RequiredMode.REQUIRED)
	private Contract contract;

	/**
	 * 対象ライフサイクル状態
	 */
	@Column(nullable = false)
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
	@Column(nullable = false)
	@Max(9)
	@Min(0)
	@Schema(description = "特価承認対象フラグ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9]")
	private int specialPriceApprovalFlg;

	/**
	 * 承認ルートマスタID
	 */
	@Schema(description = "承認ルートマスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long approvalRouteMasterId;

	/**
	 * 契約承認実績
	 */
	@OneToMany(mappedBy = "contractApprovalRoute")
	@OrderBy("processedAt ASC")
	@Schema(description = "契約承認実績(作成時不要)", readOnly = true)
	private List<ContractApprovalResult> contractApprovalResultList;

	/**
	 * 契約承認ルートノード
	 */
	@OneToMany(mappedBy = "contractApprovalRoute")
	@Schema(description = "契約承認ルートノード(作成時不要)", readOnly = true)
	@OrderBy("approvalOrder ASC")
	private List<ContractApprovalRouteNode> contractApprovalRouteNodeList;
}
