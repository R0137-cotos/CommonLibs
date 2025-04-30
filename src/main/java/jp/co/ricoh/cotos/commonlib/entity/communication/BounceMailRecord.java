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

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "バウンスメール記録ID (作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "契約ID", required = false, position = 2, allowableValues = "range[0,255]")
	private String contractId;

	/**
	 * 文書番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "文書番号<br />" //
			+ "見積⇒見積番号を設定<br />" //
			+ "契約/手配⇒契約番号を設定", required = false, position = 3, allowableValues = "range[0,255]")
	private String docNumber;

	/**
	 * 契約番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "契約番号", required = false, position = 4, allowableValues = "range[0,255]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@Max(99)
	@Min(0)
	@ApiModelProperty(value = "契約番号枝番", required = false, position = 5, allowableValues = "range[0,99]")
	private Integer contractBranchNumber;

	/**
	 * メールテンプレートマスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "メールテンプレートマスタID", required = false, position = 6, allowableValues = "range[0,9223372036854775807]")
	private Long mailTemplateMasterId;

	/**
	 * 送信元メール送信日時
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "送信元メール送信日時", required = false, position = 7)
	@Temporal(TemporalType.TIMESTAMP)
	private Date sentAt;

	/**
	 * N_X_CONTRACT_ID
	 */
	@Size(max = 255)
	@Column(name = "n_x_contract_id")
	@ApiModelProperty(value = "N_X_CONTRACT_ID", required = false, position = 8, allowableValues = "range[0,255]")
	private String nXContractId;

	/**
	 * N_X_MAILER
	 */
	@Size(max = 255)
	@Column(name = "n_x_mailer")
	@ApiModelProperty(value = "N_X_MAILER", required = false, position = 9, allowableValues = "range[0,255]")
	private String nXMailer;

	/**
	 * N_X_NPSERVICENO
	 */
	@Min(0)
	@Column(name = "n_x_npserviceno")
	@ApiModelProperty(value = "N_X_NPSERVICENO", required = false, position = 10, allowableValues = "range[0,9223372036854775807]")
	private Long nXNpserviceno;

	/**
	 * N_X_NSERVICELINENO
	 */
	@Min(0)
	@Column(name = "n_x_nservicelineno")
	@ApiModelProperty(value = "N_X_NSERVICELINENO", required = false, position = 11, allowableValues = "range[0,9223372036854775807]")
	private Long nXNservicelineno;

	/**
	 * N_X_NDOMAINLINENO
	 */
	@Min(0)
	@Column(name = "n_x_ndomainlineno")
	@ApiModelProperty(value = "N_X_NDOMAINLINENO", required = false, position = 12, allowableValues = "range[0,9223372036854775807]")
	private Long nXNdomainlineno;

	/**
	 * N_X_NGUIDETARGETTYPE
	 */
	@Size(max = 255)
	@Column(name = "n_x_nguidetargettype")
	@ApiModelProperty(value = "N_X_NGUIDETARGETTYPE", required = false, position = 13, allowableValues = "range[0,255]")
	private String nXNguidetargettype;

	/**
	 * N_X_JIZENFLG
	 */
	@Size(max = 255)
	@Column(name = "n_x_jizenflg")
	@ApiModelProperty(value = "N_X_JIZENFLG", required = false, position = 14, allowableValues = "range[0,255]")
	private String nXJizenflg;

	/**
	 * N_ERROR_FLG
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "N_ERROR_FLG", required = false, position = 15, allowableValues = "range[0,255]")
	private String nErrorFlg;

	/**
	 * バウンスメール宛先
	 */
	@Valid
	@OneToMany(mappedBy = "bounceMailRecord")
	@ApiModelProperty(value = "バウンスメール宛先", required = false, position = 16)
	private List<BounceMailDestination> bounceMailDestinationList;

	/**
	 * COTOS運用対応完了済フラグ
	 */
	@Max(9)
	@ApiModelProperty(value = "COTOS運用対応完了済フラグ", required = false, position = 17, allowableValues = "1:COTOS運用チームに残作業がない。")
	private Integer cotosSupportCompleted;

	/**
	 * 通知メール制御マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "通知メール制御マスタID", required = false, position = 18)
	private Long mailControlMasterId;

	/**
	 * サービスカテゴリ
	 */
	@ApiModelProperty(value = "サービスカテゴリ", required = false, allowableValues = "見積(\"1\"), 契約(\"2\"), 手配(\"3\")", position = 19)
	private ServiceCategory serviceCategory;

	/**
	 * 対象文書キー
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "対象文書キー<br />コミュニケーションの対象情報を一意に特定するためのキー情報を格納<br />" //
			+ "見積⇒見積ID<br />" //
			+ "契約⇒契約ID<br />" //
			+ "手配⇒手配業務ID", required = false, position = 20, allowableValues = "range[0,255]") //
	private String targetDocKey;

}
