package jp.co.ricoh.cotos.commonlib.entity.arrangement;

import java.util.Date;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 手配業務Entity作成時のチェック結果を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "arrangement_work_check_result")
public class ArrangementWorkCheckResult extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arrangement_work_check_result_seq")
	@SequenceGenerator(name = "arrangement_work_check_result_seq", sequenceName = "arrangement_work_check_result_seq", allocationSize = 1)
	@Schema(description = "手配業務チェック結果ID (作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 手配業務
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "arrangement_work_id", referencedColumnName = "id")
	@Schema(description = "手配業務", requiredMode = Schema.RequiredMode.REQUIRED)
	@JsonIgnore
	private ArrangementWork arrangementWork;

	/**
	 * チェック事項コード
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "チェック事項コード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String checkMatterCode;

	/**
	 * チェック事項文面
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "チェック事項文面", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String checkMatterText;

	/**
	 * 表示順
	 */
	@Max(999)
	@Min(0)
	@OrderBy("desc")
	@Column(nullable = false)
	@Schema(description = "表示順", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,999]")
	private int displayOrder;

	/**
	 * チェック実施者
	 */
	@Size(max = 255)
	@Schema(description = "チェック実施者", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String checkedUserId;

	/**
	 * チェック実施者氏名
	 */
	@Size(max = 255)
	@Schema(description = "チェック実施者氏名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String checkedUserName;

	/**
	 * チェック実施者組織名
	 */
	@Size(max = 255)
	@Schema(description = "チェック実施者組織名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String checkedOrgName;

	/**
	 * チェック実施日時
	 */
	@Schema(description = "チェック実施日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkedAt;

}
