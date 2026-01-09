package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 手配業務タイプマスタを表すEntity
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "arrangement_work_type_master")
public class ArrangementWorkTypeMaster extends EntityBaseMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arrangement_work_type_master_seq")
	@SequenceGenerator(name = "arrangement_work_type_master_seq", sequenceName = "arrangement_work_type_master_seq", allocationSize = 1)
	@Schema(description = "手配業務タイプマスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 手配業務タイプ名
	 */
	@Column(nullable = false)
	@Schema(description = "手配業務タイプ名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String arrangementWorkName;

	/**
	 * 説明
	 */
	@Schema(description = "説明", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String description;

	/**
	 * 承認ルートグループマスタ
	 */
	@ManyToOne
	@JoinColumn(name = "arrangement_approval_route_grp_id", referencedColumnName = "id")
	@Schema(description = "承認ルートグループマスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ApprovalRouteGrpMaster approvalRouteGrpMaster;

	/**
	 * 手配業務構成マスタ
	 */
	@OneToMany(mappedBy = "arrangementWorkTypeMaster")
	@Schema(description = "手配業務タイプマスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<ArrangementWorkCompMaster> arrangementWorkCompMasterList;

	/**
	 * 手配チェックリスト構成マスタ
	 */
	@OneToMany(mappedBy = "arrangementWorkTypeMaster")
	@Schema(description = "手配チェックリスト構成マスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<ArrangementChecklistCompMaster> arrangementChecklistCompMasterList;

	/**
	 * アプリケーションID
	 */
	@Size(max = 255)
	@Schema(description = "アプリケーションID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String appId;

	/**
	 * 手配業務タイプ区分
	 */
	@Size(max = 255)
	@Schema(description = "手配業務タイプ区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String arrangementWorkTypeDiv;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameter;

	/**
	 * 手配業務権限制御マスタ
	 */
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "arrangement_work_auth_control_master_id", referencedColumnName = "id")
	@Schema(description = "手配業務権限制御マスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ArrangementWorkAuthControlMaster arrangementWorkAuthControlMaster;

	/**
	 * 契約フロー考慮不要フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "契約フロー考慮不要フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer contractFlowUnnecessaryFlg;
}
