package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ApprovalTargetType;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ProcessCategory;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ServiceCategory;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.WorkflowType;
import lombok.Data;

/**
 * コミュニケーションをリスト取得するためのDtoです。<br/>
 * 一覧を取得するためには、こちらのクラスを使ってください。 <br/>
 * このクラスを使用してDBへの保存を行うことは出来ません。
 */
@Entity
@Data
public class CommunicationListInfo {

	@Id
	@Schema(description = "連番", required = true)
	private long seqNo;

	@Schema(description = "コミュニケーションID", required = true)
	private long id;

	/**
	 * サービスカテゴリ
	 */
	@Schema(description = "サービスカテゴリ", required = true, allowableValues = "見積(\"1\"), 契約(\"2\"), 手配(\"3\")", example = "1")
	private ServiceCategory serviceCategory;

	/**
	 * 処理カテゴリー
	 */
	@Schema(description = "処理カテゴリー", required = true, allowableValues = "承認依頼(\"1\"), 承認依頼取消(\"2\"), 承認依頼差戻(\"3\"), 承認(\"4\"), 作業依頼(\"5\"), 作業完了(\"6\"), キャンセル手続き(\"7\"), キャンセル手続き中止(\"8\"), 解約手続き(\"9\"), 解約手続き中止(\"10\")", example = "1")
	private ProcessCategory processCategory;

	/**
	 * 対象文書キー
	 */
	@Schema(description = "対象文書キー<br />コミュニケーションの対象情報を一意に特定するためのキー情報を格納<br />" //
			+ "見積⇒見積ID<br />" //
			+ "契約⇒契約ID<br />" //
			+ "手配⇒手配業務ID", required = true, allowableValues = "range[0,255]") //
	private String targetDocKey;

	/**
	 * ワークフロー種別
	 */
	@Schema(description = "ワークフロー種別", required = true, allowableValues = "承認フロー(\"1\"), タスクフロー(\"2\")", example = "1")
	private WorkflowType workflowType;

	/**
	 * 承認対象種別
	 */
	@Schema(description = "承認対象種別<br />" //
			+ "承認フロー⇒新規/情報変更/契約変更/キャンセル/解約/作業完了報告/契約更新<br />" //
			+ "タスクフロー⇒非承認", required = true, allowableValues = "新規(\"1\"), 情報変更(\"2\"), 契約変更(\"3\"), キャンセル(\"4\"), 解約(\"5\"), 作業完了報告(\"6\"), 非承認(\"7\"), 売上指示(\"8\"), 売上計上(\"9\"), 承認済差戻(\"10\"), 契約更新(\"11\")", example = "1") //
	private ApprovalTargetType approvalTargetType;

	/**
	 * 対象文書画面URL
	 */
	@Schema(description = "対象文書画面URL<br />Home画面でレコードクリック時の画面遷移先URLを格納<br />" //
			+ "見積⇒対象文書キーで特定される見積情報画面のURL<br />" //
			+ "契約⇒対象文書キーで特定される契約情報画面のURL<br />" //
			+ "手配⇒対象文書キーで特定される手配業務情報画面のURL", required = true, allowableValues = "range[0,1000]") //
	private String targetDocUrl;

	/**
	 * 依頼者
	 */
	@Schema(description = "依頼者", required = true, allowableValues = "range[0,255]") //
	private String requestOriginId;

	/**
	 * 伝達者
	 */
	@Schema(description = "伝達者", required = true, allowableValues = "range[0,255]") //
	private String requestFromId;

	/**
	 * 被伝達者
	 */
	@Schema(description = "被伝達者", required = true, allowableValues = "range[0,255]") //
	private String requestToId;

	/**
	 * 被伝達者候補
	 */
	@Schema(description = "被伝達者候補", required = false, allowableValues = "range[0,255]") //
	private String requestToCandidateId;

	/**
	 * 対象文書番号
	 */
	@Schema(description = "対象文書番号<br />" //
			+ "見積⇒見積番号を設定<br />" //
			+ "契約/手配⇒契約番号を設定", required = true, allowableValues = "range[0,255]")
	private String targetDocNumber;

	/**
	 * 対象文書番号枝番
	 */
	@Schema(description = "対象文書番号枝番<br />" //
			+ "見積⇒見積番号枝番を設定<br />" //
			+ "契約/手配⇒契約番号枝番を設定", required = true, allowableValues = "range[0,99]")
	private int targetDocBranchNumber;

	/**
	 * 顧客名
	 */
	@Schema(description = "顧客名", required = true, allowableValues = "range[0,255]")
	private String customerName;

	/**
	 * 商品グループ名
	 */
	@Schema(description = "商品グループ名<br />" //
			+ "商品グループマスタの商品グループ名を設定", required = false, allowableValues = "range[0,255]") //
	private String productGrpName;

	/**
	 * 件名
	 */
	@Schema(description = "件名<br />" + "見積⇒見積の案件名を設定<br />" //
			+ "契約⇒契約の案件名を設定<br />" //
			+ "手配⇒手配業務タイプマスタの手配業務タイプ名を設定", required = false, allowableValues = "range[0,255]") //
	private String title;

	/**
	 * コメント
	 */
	@Schema(description = "コメント", required = false, allowableValues = "range[0,255]")
	private String communicationComment;

	/**
	 * 伝達日時
	 */
	@Schema(description = "伝達日時", required = true, readOnly = true)
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
	@Schema(description = "依頼者氏名", required = false, allowableValues = "range[0,255]", readOnly = true)
	private String requestOriginName;

	/**
	 * 伝達者
	 */
	@Schema(description = "伝達者", required = false, allowableValues = "range[0,255]", readOnly = true)
	private String requestFromName;

	/**
	 * 被伝達者
	 */
	@Schema(description = "被伝達者", required = false, allowableValues = "range[0,255]", readOnly = true)
	private String requestToName;

	/**
	 * 被伝達者候補
	 */
	@Schema(description = "被伝達者候補", required = false, allowableValues = "range[0,255]", readOnly = true)
	private String requestToCandidateName;

	/**
	 * アプリケーションID
	 */
	@Schema(description = "アプリケーションID", required = false, allowableValues = "range[0,255]")
	private String appId;

	/**
	 * 見積種別／契約種別
	 */
	@Schema(description = "見積種別／契約種別", required = false, allowableValues = "range[0,255]")
	private String type;

	/**
	 * 所属（承認依頼者）
	 */
	@Schema(description = "所属（承認依頼者）", required = false, allowableValues = "range[0,255]")
	private String orgName;

	/**
	 * 事業所名
	 */
	@Schema(description = "事業所名", required = false, allowableValues = "range[0,255]")
	private String officeName;

	@PrePersist
	public void prePersist() {
		throw new IllegalAccessError();
	}
}
