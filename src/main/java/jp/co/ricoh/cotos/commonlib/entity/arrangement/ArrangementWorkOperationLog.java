package jp.co.ricoh.cotos.commonlib.entity.arrangement;

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
 * 手配業務操作履歴を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(ArrangementWorkOperationLogListener.class)
@Data
@Table(name = "arrangement_work_operation_log")
public class ArrangementWorkOperationLog extends EntityBase {

	@Description(value = "操作内容(手配業務操作履歴)")
	public enum Operation {
		業務受付, 業務受付取消, 作業完了報告, 新規作成, 担当作業者設定, 更新, 作業完了, 業務完了取消, 保留更新, エラー発生, エラー解消, 業務指定作成, 帳票出力, 手配実施, 手配破棄, マネージド作業受付, ライセンス管理受付, CSV出力, CSV取込, 当日設定, 減数作業受付, 事前設定, 詳細設定, ライセンス割当受付, ライセンス割当, 同梱品確認書出力, お問合せ伝票番号設定, マネージド作業, Welcomeメール受付, Welcomeメール送信, Welcomeメール完了, ライセンス発注受付, ライセンス発注, Welcomeメール送信受付, Welcomeメール不達確認, リモート導入事前作業, 初期設定, モバイル受付, オーダCSV送信, 解約CSV送信, リプライCSV取込, ISP設定完了メール受付, ISP設定完了メール送信, ISP設定完了メール不達確認,
		作業完了メール送信受付, 作業完了メール送信, 作業完了メール不達確認
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arrangement_work_operation_log_seq")
	@SequenceGenerator(name = "arrangement_work_operation_log_seq", sequenceName = "arrangement_work_operation_log_seq", allocationSize = 1)
	@Schema(description = "手配業務操作履歴ID (作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 手配業務
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "arrangement_work_id", referencedColumnName = "id")
	@Schema(description = "手配業務", required = true)
	@JsonIgnore
	private ArrangementWork arrangementWork;

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
