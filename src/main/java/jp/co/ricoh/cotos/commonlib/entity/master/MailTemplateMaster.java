package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * メールテンプレートマスタ
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "mail_template_master")
public class MailTemplateMaster extends EntityBaseMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mail_template_master_seq")
	@SequenceGenerator(name = "mail_template_master_seq", sequenceName = "mail_template_master_seq", allocationSize = 1)
	@Schema(description = "メールテンプレートID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * サービスカテゴリ
	 */
	@Column(nullable = false)
	@Schema(description = "サービスカテゴリ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "見積(\"1\"), 契約(\"2\"), 手配(\"3\")", example = "1")
	private ServiceCategory serviceCategory;

	/**
	 * 処理カテゴリ
	 */
	@Column(nullable = false)
	@Schema(description = "処理カテゴリ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "承認依頼(\"1\"), 承認依頼取消(\"2\"), 承認依頼差戻(\"3\"), 承認(\"4\"), 作業依頼(\"5\"), 作業完了(\"6\"), キャンセル手続き(\"7\"), キャンセル手続き中止(\"8\"), 解約手続き(\"9\"), 解約手続き中止(\"10\"), 問い合わせ(\"11\"), 100(お客様担当者), 101(接点店担当者), 102(母店接点店担当者)", example = "1")
	private String processCategory;

	/**
	 * 商品グループマスタID
	 */
	@Column(nullable = false)
	@Schema(description = "商品グループマスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long productGrpMasterId;

	/**
	 * メール件名
	 */
	@Column(nullable = false)
	@Schema(description = "メール件名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String mailSubject;

	/**
	 * メール本文
	 */
	@Column(nullable = false)
	@Lob
	@Schema(description = "メール本文", requiredMode = Schema.RequiredMode.REQUIRED)
	private String mailBody;

	/**
	 * メール制御マスタ
	 */
	@OneToMany(mappedBy = "mailTemplateMaster")
	@Schema(description = "メール制御マスタ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<MailControlMaster> mailControlMasterList;

	/**
	 * バウンスメール戻り先
	 */
	@Column
	@Schema(description = "バウンスメール戻り先", requiredMode = Schema.RequiredMode.REQUIRED)
	private String envelopeFrom;

	/**
	 * fromメールアドレス
	 */
	@Column
	@Schema(description = "fromメールアドレス", requiredMode = Schema.RequiredMode.REQUIRED)
	private String fromMailAddress;

	/**
	 * 添付ファイルパス
	 */
	@Column
	@Schema(description = "添付ファイルパス", requiredMode = Schema.RequiredMode.REQUIRED)
	private String attachedFilePath;
}
