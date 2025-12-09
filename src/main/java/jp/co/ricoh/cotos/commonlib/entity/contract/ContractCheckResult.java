package jp.co.ricoh.cotos.commonlib.entity.contract;

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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.LifecycleStatus;
import jp.co.ricoh.cotos.commonlib.serializer.UnixTimestampDateSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約チェック結果を表すEntity
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "contract_check_result")
public class ContractCheckResult extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_check_result_seq")
	@SequenceGenerator(name = "contract_check_result_seq", sequenceName = "contract_check_result_seq", allocationSize = 1)
	@Schema(description = "契約チェック結果ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約
	 */
	@ManyToOne(optional = false)
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
	@NotNull
	@Size(max = 255)
	@Column(nullable = false)
	@Schema(description = "チェック事項文面", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String checkMatterText;

	/**
	 * 表示順
	 */
	@Column(nullable = false)
	@OrderBy("desc")
	@Max(999)
	@Min(0)
	@Schema(description = "表示順", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,999]")
	private int displayOrder;

	/**
	 * チェック実施者MoM社員ID
	 */
	@Size(max = 255)
	@Schema(description = "チェック実施者MoM社員ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
	@JsonSerialize(using = UnixTimestampDateSerializer.class)
	@Schema(description = "チェック実施日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date checkedAt;
}
