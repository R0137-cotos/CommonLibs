package jp.co.ricoh.cotos.commonlib.entity.estimation;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 見積操作履歴を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(OperationLogListener.class)
@Data
@Table(name = "operation_log")
public class OperationLog extends EntityBase {

	@Description(value = "操作内容(見積操作履歴)")
	public enum Operation {
		キャンセル, 新規作成, 帳票出力, 新規作成_再見積, 新規作成_コピー, 新規作成_契約変更, 受注, 失注, 更新, 業務依頼, 業務処理完了, 破棄, 初期費情報更新, 移行データ_新規作成, 移行データ_更新, 新規作成_明細追加, 契約作成失敗_状態更新, 価格改定_破棄, 電子契約文書登録, 電子契約文書削除, 価格書換_破棄
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operation_log_seq")
	@SequenceGenerator(name = "operation_log_seq", sequenceName = "operation_log_seq", allocationSize = 1)
	@Schema(description = "操作履歴ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 見積
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "estimation_id", referencedColumnName = "id")
	@Schema(description = "見積", required = true)
	@JsonIgnore
	private Estimation estimation;

	/**
	 * 操作内容
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "操作内容", required = true, allowableValues = "range[0,1000]")
	@Enumerated(EnumType.STRING)
	private Operation operation;

	/**
	 * 操作者MoM社員ID
	 */
	@NotNull
	@Column(nullable = false)
	@Schema(description = "操作者MoM社員ID<br/>※POST時「RJ社員情報マスタ」存在チェック実施", required = true)
	private String operatorEmpId;

	/**
	 * 操作者氏名
	 */
	@Size(max = 255)
	@Schema(description = "操作者氏名", required = false, allowableValues = "range[0,255]")
	private String operatorName;

	/**
	 * 操作者組織名
	 */
	@Size(max = 255)
	@Schema(description = "操作者組織名", required = false, allowableValues = "range[0,255]")
	private String operatorOrgName;

	/**
	 * 実施日時
	 */
	@Column(nullable = false)
	@Schema(description = "実施日時(作成時不要)", required = true, readOnly = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date operatedAt;

	@PrePersist
	public void prePersist() {
		super.prePersist();
		this.operatedAt = super.getCreatedAt();
	}
}
