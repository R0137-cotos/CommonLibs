package jp.co.ricoh.cotos.commonlib.entity.communication;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ApprovalTargetType;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ProcessCategory;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.WorkflowType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * コミュニケーション履歴を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "communication_history")
public class CommunicationHistory extends EntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "communication_history_seq")
	@SequenceGenerator(name = "communication_history_seq", sequenceName = "communication_history_seq", allocationSize = 1)
	@Schema(description = "ID (作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * サービスカテゴリ
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "サービスカテゴリ", required = true, allowableValues = "見積(\"1\"), 契約(\"2\"), 手配(\"3\")", example = "1")
	private ServiceCategory serviceCategory;

	/**
	 * 処理カテゴリー
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "処理カテゴリー", required = true, allowableValues = "承認依頼(\"1\"), 承認依頼取消(\"2\"), 承認依頼差戻(\"3\"), 承認(\"4\"), 作業依頼(\"5\"), 作業完了(\"6\"), キャンセル手続き(\"7\"), キャンセル手続き中止(\"8\"), 解約手続き(\"9\"), 解約手続き中止(\"10\")", example = "1")
	private ProcessCategory processCategory;

	/**
	 * 対象文書キー
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "対象文書キー<br />コミュニケーションの対象情報を一意に特定するためのキー情報を格納<br />" //
			+ "見積⇒見積ID<br />" //
			+ "契約⇒契約ID<br />" //
			+ "手配⇒手配業務ID", required = true, allowableValues = "range[0,255]") //
	private String targetDocKey;

	/**
	 * ワークフロー種別
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "ワークフロー種別", required = true, allowableValues = "承認フロー(\"1\"), タスクフロー(\"2\")", example = "1")
	private WorkflowType workflowType;

	/**
	 * 承認対象種別
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "承認対象種別<br />" //
			+ "承認フロー⇒新規/情報変更/契約変更/キャンセル/解約/作業完了報告/契約更新<br />" //
			+ "タスクフロー⇒非承認", required = true, allowableValues = "新規(\"1\"), 情報変更(\"2\"), 契約変更(\"3\"), キャンセル(\"4\"), 解約(\"5\"), 作業完了報告(\"6\"), 非承認(\"7\"), 売上指示(\"8\"), 売上計上(\"9\"), 承認済差戻(\"10\"), 契約更新(\"11\")", example = "1") //
	private ApprovalTargetType approvalTargetType;

	/**
	 * 対象文書画面URL
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 1000)
	@Schema(description = "対象文書画面URL<br />Home画面でレコードクリック時の画面遷移先URLを格納<br />" //
			+ "見積⇒対象文書キーで特定される見積情報画面のURL<br />" //
			+ "契約⇒対象文書キーで特定される契約情報画面のURL<br />" //
			+ "手配⇒対象文書キーで特定される手配業務情報画面のURL", required = true, allowableValues = "range[0,1000]") //
	private String targetDocUrl;

	/**
	 * 依頼者
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "依頼者 (作成時不要)<br />" //
			+ "ワークフローの起点となったユーザーのMoM社員IDを設定", required = true, allowableValues = "range[0,255]") //
	private String requestOriginId;

	/**
	 * 伝達者
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "伝達者<br />" //
			+ "ユーザー識別子としてMoM社員IDを設定", required = true, allowableValues = "range[0,255]") //
	private String requestFromId;

	/**
	 * 被伝達者
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "被伝達者<br />" //
			+ "ユーザー識別子としてMoM社員IDを設定", required = true, allowableValues = "range[0,255]") //
	private String requestToId;

	/**
	 * 被伝達者候補
	 */
	@Size(max = 255)
	@Schema(description = "被伝達者候補<br />" //
			+ "ユーザー識別子としてMoM社員IDを設定", required = false, allowableValues = "range[0,255]") //
	private String requestToCandidateId;

	/**
	 * 対象文書番号
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "対象文書番号<br />" //
			+ "見積⇒見積番号を設定<br />" //
			+ "契約/手配⇒契約番号を設定", required = true, allowableValues = "range[0,255]")
	private String targetDocNumber;

	/**
	 * 対象文書番号枝番
	 */
	@Max(99)
	@Min(0)
	@Column(nullable = false)
	@Schema(description = "対象文書番号枝番<br />" //
			+ "見積⇒見積番号枝番を設定<br />" //
			+ "契約/手配⇒契約番号枝番を設定", required = true, allowableValues = "range[0,99]")
	private int targetDocBranchNumber;

	/**
	 * 顧客名
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "顧客名", required = true, allowableValues = "range[0,255]")
	private String customerName;

	/**
	 * 商品グループ名
	 */
	@Size(max = 255)
	@Schema(description = "商品グループ名<br />" //
			+ "商品グループマスタの商品グループ名を設定", required = false, allowableValues = "range[0,255]") //
	private String productGrpName;

	/**
	 * 件名
	 */
	@Size(max = 255)
	@Schema(description = "件名<br />" + "見積⇒見積の案件名を設定<br />" //
			+ "契約⇒契約の案件名を設定<br />" //
			+ "手配⇒手配業務タイプマスタの手配業務タイプ名を設定", required = false, allowableValues = "range[0,255]") //
	private String title;

	/**
	 * コメント
	 */
	@Size(max = 255)
	@Schema(description = "コメント", required = false, allowableValues = "range[0,255]")
	private String communicationComment;

	/**
	 * 伝達日時
	 */
	@Column(nullable = false)
	@Schema(description = "伝達日時 (作成時不要)", required = true, readOnly = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date communicatedAt;

	/**
	 * 商品グループマスタID
	 */
	@Schema(description = "商品グループマスタID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long productGrpMasterId;

	/**
	 * 依頼者氏名
	 */
	@Schema(description = "依頼者氏名 (作成時不要)", required = false, allowableValues = "range[0,255]", readOnly = true)
	private String requestOriginName;

	/**
	 * 伝達者
	 */
	@Schema(description = "伝達者 (作成時不要)", required = false, allowableValues = "range[0,255]", readOnly = true)
	private String requestFromName;

	/**
	 * 被伝達者
	 */
	@Schema(description = "被伝達者 (作成時不要)", required = false, allowableValues = "range[0,255]", readOnly = true)
	private String requestToName;

	/**
	 * 被伝達者候補
	 */
	@Schema(description = "被伝達者候補 (作成時不要)", required = false, allowableValues = "range[0,255]", readOnly = true)
	private String requestToCandidateName;

	/**
	 * アプリケーションID
	 */
	@Size(max = 255)
	@Schema(description = "アプリケーションID", required = false, allowableValues = "range[0,255]")
	private String appId;

	@PrePersist
	public void prePersist() {
		super.prePersist();
		this.communicatedAt = super.getCreatedAt();
	}
}
