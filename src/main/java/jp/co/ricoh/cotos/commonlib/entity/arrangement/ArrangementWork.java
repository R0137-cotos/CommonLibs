package jp.co.ricoh.cotos.commonlib.entity.arrangement;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 手配業務情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true, exclude = {"arrangementWorkApprovalRoute", "arrangementPicWorkerEmp", "arrangementWorkOperationLogList", "workAttachedFileList", "arrangementWorkCheckResultList", "arrangementWorkErrorLogList"})
@ToString(callSuper = true, exclude = {"arrangementWorkApprovalRoute", "arrangementPicWorkerEmp", "arrangementWorkOperationLogList", "workAttachedFileList", "arrangementWorkCheckResultList", "arrangementWorkErrorLogList"})
@Data
@Table(name = "arrangement_work")
public class ArrangementWork extends EntityBase {

	@Description(value = "ワークフロー状態(手配業務)")
	public enum WorkflowStatus {

		受付待ち("1"), 作業中("2"), 作業完了報告("3"), 承認依頼中("4"), 作業完了("5"), エラー("6"), 破棄("7");

		private final String text;

		private WorkflowStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static WorkflowStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arrangement_work_seq")
	@SequenceGenerator(name = "arrangement_work_seq", sequenceName = "arrangement_work_seq", allocationSize = 1)
	@Schema(description = "手配業務ID (作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 手配
	 */
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "arrangement_id", referencedColumnName = "id")
	@Schema(description = "手配", required = true)
	private Arrangement arrangement;

	/**
	 * 手配業務タイプマスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "手配業務タイプマスタID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long arrangementWorkTypeMasterId;

	/**
	 * ワークフロー状態
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "ワークフロー状態", required = true, allowableValues = "受付待ち(\"1\"), 作業中(\"2\"), 作業完了報告(\"3\"), 承認依頼中(\"4\"), 作業完了(\"5\"), エラー(\"6\"), 破棄(\"7\")", example = "1")
	private WorkflowStatus workflowStatus;

	/**
	 * メモ
	 */
	@Size(max = 4000)
	@Schema(description = "メモ", required = false, allowableValues = "range[0,4000]")
	private String memo;

	/**
	 * 保留フラグ
	 */
	@Column(nullable = false)
	@Max(9)
	@Min(0)
	@Schema(description = "保留フラグ", required = true, allowableValues = "range[0,9]")
	private int holdingFlg;

	/**
	 * 手配業務承認ルート
	 */
	@OneToOne(mappedBy = "arrangementWork")
	@Schema(description = "手配業務承認ルート", required = false)
	private ArrangementWorkApprovalRoute arrangementWorkApprovalRoute;

	/**
	 * 担当作業者社員
	 */
	@OneToOne(mappedBy = "arrangementWork")
	@Schema(description = "担当作業者社員", required = false)
	private ArrangementPicWorkerEmp arrangementPicWorkerEmp;

	/**
	 * 手配業務操作履歴
	 */
	@OneToMany(mappedBy = "arrangementWork")
	@OrderBy("operatedAt ASC")
	@Schema(description = "手配業務操作履歴", required = false)
	private List<ArrangementWorkOperationLog> arrangementWorkOperationLogList;

	/**
	 * 手配業務添付ファイル
	 */
	@OneToMany(mappedBy = "arrangementWork")
	@Schema(description = "手配業務添付ファイル", required = false)
	private List<ArrangementWorkAttachedFile> workAttachedFileList;

	/**
	 * 手配業務チェック結果
	 */
	@OneToMany(mappedBy = "arrangementWork")
	@OrderBy("displayOrder ASC")
	@Schema(description = "手配業務チェック結果", required = false)
	private List<ArrangementWorkCheckResult> arrangementWorkCheckResultList;

	/**
	 * 手配業務エラー履歴
	 */
	@OneToMany(mappedBy = "arrangementWork")
	@Schema(description = "手配業務エラー履歴", required = false)
	private List<ArrangementWorkErrorLog> arrangementWorkErrorLogList;

	/**
	 * アプリケーションID
	 */
	@Size(max = 255)
	@Schema(description = "アプリケーションID", required = false, allowableValues = "range[0,255]")
	private String appId;

	/**
	 * 作業完了日時
	 */
	@Schema(description = "作業完了日時", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date workCompletedAt;

	/**
	 * 業務受理日時
	 */
	@Schema(description = "業務受理日時", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date businessAcceptanceDateTime;

	/**
	 * 業務受付日時
	 */
	@Schema(description = "業務受付日時", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date businessAcceptDateTime;

	/**
	 * 業務受付枝番
	 */
	@Max(99)
	@Min(1)
	@Schema(description = "業務受付枝番", required = false, allowableValues = "range[1,99]")
	private Integer businessAcceptBranchNumber;

}