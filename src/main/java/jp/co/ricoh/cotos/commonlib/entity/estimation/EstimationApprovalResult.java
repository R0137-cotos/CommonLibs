package jp.co.ricoh.cotos.commonlib.entity.estimation;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ApprovalProcessCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 見積承認実績を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(EstimationApprovalResultListener.class)
@Data
@Table(name = "estimation_approval_result")
public class EstimationApprovalResult extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estimation_approval_result_seq")
	@SequenceGenerator(name = "estimation_approval_result_seq", sequenceName = "estimation_approval_result_seq", allocationSize = 1)
	@Schema(description = "見積承認実績ID(作成時不要)", required = true, allowableValues = "range[0,9999999999999999999]", readOnly = true)
	private long id;

	/**
	 * 見積承認ルート
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "estimation_approval_route_id", referencedColumnName = "id")
	@Schema(description = "見積承認ルート", requiredMode = Schema.RequiredMode.REQUIRED)
	@JsonIgnore
	private EstimationApprovalRoute estimationApprovalRoute;

	/**
	 * 見積承認ルートノードID
	 */
	@Schema(description = "見積承認ルートノードID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long estimationApprovalRouteNodeId;

	/**
	 * 承認処理カテゴリー
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "承認処理カテゴリー", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "承認依頼(\"1\"), 承認依頼差戻(\"2\"), 承認(\"3\"), 承認依頼取消(\"4\")", example = "1")
	private ApprovalProcessCategory approvalProcessCategory;

	/**
	 * 処理実施者MoM社員ID
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "処理実施者MoM社員ID<br/>※POST時「RJ社員情報マスタ」存在チェック実施", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String actualEmpId;

	/**
	 * 処理実施者社員名
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "処理実施者社員名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String actualUserName;

	/**
	 * 処理実施者組織名
	 */
	@Size(max = 255)
	@Schema(description = "処理実施者組織名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String actualOrgName;

	/**
	 * コメント
	 */
	@Size(max = 255)
	@Schema(description = "コメント", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String requestComment;

	/**
	 * 実施日時
	 */
	@Column(nullable = false)
	@Schema(description = "実施日時(作成時不要)", required = true, readOnly = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date processedAt;

	@PrePersist
	public void prePersist() {
		super.prePersist();
		this.processedAt = super.getCreatedAt();
	}

}
