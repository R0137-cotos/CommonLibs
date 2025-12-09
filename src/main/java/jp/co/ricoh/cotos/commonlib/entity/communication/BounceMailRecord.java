package jp.co.ricoh.cotos.commonlib.entity.communication;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * バウンスメール記録を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "bounce_mail_record")
public class BounceMailRecord extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bounce_mail_record_seq")
	@SequenceGenerator(name = "bounce_mail_record_seq", sequenceName = "bounce_mail_record_seq", allocationSize = 1)
	@Schema(description = "バウンスメール記録ID (作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Size(max = 255)
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractId;

	/**
	 * 文書番号
	 */
	@Size(max = 255)
	@Schema(description = "文書番号<br />" //
			+ "見積⇒見積番号を設定<br />" //
			+ "契約/手配⇒契約番号を設定", required = false, allowableValues = "range[0,255]")
	private String docNumber;

	/**
	 * 契約番号
	 */
	@Size(max = 255)
	@Schema(description = "契約番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@Max(99)
	@Min(0)
	@Schema(description = "契約番号枝番", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99]")
	private Integer contractBranchNumber;

	/**
	 * メールテンプレートマスタID
	 */
	@Min(0)
	@Schema(description = "メールテンプレートマスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long mailTemplateMasterId;

	/**
	 * 送信元メール送信日時
	 */
	@Column(nullable = false)
	@Schema(description = "送信元メール送信日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.TIMESTAMP)
	private Date sentAt;

	/**
	 * N_X_CONTRACT_ID
	 */
	@Size(max = 255)
	@Column(name = "n_x_contract_id")
	@Schema(description = "N_X_CONTRACT_ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nXContractId;

	/**
	 * N_X_MAILER
	 */
	@Size(max = 255)
	@Column(name = "n_x_mailer")
	@Schema(description = "N_X_MAILER", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nXMailer;

	/**
	 * N_X_NPSERVICENO
	 */
	@Min(0)
	@Column(name = "n_x_npserviceno")
	@Schema(description = "N_X_NPSERVICENO", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long nXNpserviceno;

	/**
	 * N_X_NSERVICELINENO
	 */
	@Min(0)
	@Column(name = "n_x_nservicelineno")
	@Schema(description = "N_X_NSERVICELINENO", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long nXNservicelineno;

	/**
	 * N_X_NDOMAINLINENO
	 */
	@Min(0)
	@Column(name = "n_x_ndomainlineno")
	@Schema(description = "N_X_NDOMAINLINENO", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long nXNdomainlineno;

	/**
	 * N_X_NGUIDETARGETTYPE
	 */
	@Size(max = 255)
	@Column(name = "n_x_nguidetargettype")
	@Schema(description = "N_X_NGUIDETARGETTYPE", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nXNguidetargettype;

	/**
	 * N_X_JIZENFLG
	 */
	@Size(max = 255)
	@Column(name = "n_x_jizenflg")
	@Schema(description = "N_X_JIZENFLG", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nXJizenflg;

	/**
	 * N_ERROR_FLG
	 */
	@Size(max = 255)
	@Schema(description = "N_ERROR_FLG", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nErrorFlg;

	/**
	 * バウンスメール宛先
	 */
	@Valid
	@OneToMany(mappedBy = "bounceMailRecord")
	@Schema(description = "バウンスメール宛先", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<BounceMailDestination> bounceMailDestinationList;

	/**
	 * COTOS運用対応完了済フラグ
	 */
	@Max(9)
	@Schema(description = "COTOS運用対応完了済フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "1:COTOS運用チームに残作業がない。")
	private Integer cotosSupportCompleted;

	/**
	 * 通知メール制御マスタID
	 */
	@Min(0)
	@Schema(description = "通知メール制御マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long mailControlMasterId;

	/**
	 * サービスカテゴリ
	 */
	@Schema(description = "サービスカテゴリ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "見積(\"1\"), 契約(\"2\"), 手配(\"3\")")
	private ServiceCategory serviceCategory;

	/**
	 * 対象文書キー
	 */
	@Size(max = 255)
	@Schema(description = "対象文書キー<br />コミュニケーションの対象情報を一意に特定するためのキー情報を格納<br />" //
			+ "見積⇒見積ID<br />" //
			+ "契約⇒契約ID<br />" //
			+ "手配⇒手配業務ID", required = false, allowableValues = "range[0,255]") //
	private String targetDocKey;

}
