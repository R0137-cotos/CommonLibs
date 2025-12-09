package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ApprovalProcessCategory;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.Arrangement;
import jp.co.ricoh.cotos.commonlib.entity.arrangement.ArrangementWork;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.LifecycleStatus;
import jp.co.ricoh.cotos.commonlib.serializer.UnixTimestampDateSerializer;
import lombok.Data;

/**
 * 手配をリスト取得するためのDtoです。<br/>
 * 一覧を取得するためには、こちらのクラスを使ってください。 <br/>
 * このクラスを使用してDBへの保存を行うことは出来ません。
 */
@Entity
@Data
public class ArrangementListInfo {

	@Id
	@Schema(description = "連番", required = true)
	private long seqNo;

	/**
	 * 契約ID
	 */
	@Schema(description = "契約ID", required = true)
	private long contractId;

	/**
	 * 手配ID
	 */
	@Schema(description = "手配ID", required = true)
	private long arrangementId;

	/**
	 * 手配業務ID
	 */
	@Schema(description = "手配業務ID", required = true)
	private long arrangementWorkId;

	/**
	 * 契約番号
	 */
	@Schema(description = "契約番号<br />" //
			+ "契約番号 + \"-\" + 契約番号枝番", //
			required = false, allowableValues = "range[0,18]") //
	private String contractNumber;

	/**
	 * サービス識別番号
	 */
	@Schema(description = "サービス識別番号", required = false, allowableValues = "range[0,18]")
	private String serviceIdentificationNumber;

	/**
	 * 契約状態
	 */
	@Schema(description = "契約状態<br />" //
			+ "状態遷移上のライフサイクル状態を表す。", //
			required = false, allowableValues = "作成中(\"1\"), 作成完了(\"2\"), キャンセル手続き中(\"3\"), 破棄(\"4\"), 予定日待ち(\"5\"), 締結中(\"6\"), 解約手続き中(\"7\"), 解約予定日待ち(\"8\"), 解約(\"9\"), 旧契約(\"10\"), 締結待ち(\"11\")") //
	private LifecycleStatus lifecycleStatus;

	/**
	 * 契約種別
	 */
	@Schema(description = "契約種別<br />" //
			+ "新規, 契約変更, 解約などの契約種別を表す。", //
			required = false, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")") //
	private ContractType contractType;

	/**
	 * お客様顧客名
	 */
	@Schema(description = "お客様企業名", required = false, allowableValues = "range[0,255]")
	private String customerName;

	/**
	 * 事業所名
	 */
	@Schema(description = "事業所名", required = false, allowableValues = "range[0,255]")
	private String officeName;

	/**
	 * 商品名
	 */
	@Schema(description = "商品名称", required = false, allowableValues = "range[0,255]")
	private String productName;

	/**
	 * 最終依頼審査承認履歴処理名
	 */
	@Schema(description = "最終依頼審査承認履歴処理名", required = false, allowableValues = "承認依頼(\"1\"), 承認依頼差戻(\"2\"), 承認(\"3\"), 承認依頼取消(\"4\"), 承認済差戻(\"5\")", example = "1")
	private ApprovalProcessCategory lastApprovalProcess;

	/**
	 * 希望納期
	 */
	@Schema(description = "希望納期", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = UnixTimestampDateSerializer.class)
	private Date desiredDeliveryDate;

	/**
	 * 手配業務
	 */
	@Schema(description = "手配業務", required = false, allowableValues = "range[0,255]")
	private String arrangementName;

	/**
	 * 業務受理日時
	 */
	@Schema(description = "業務受理日時", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = UnixTimestampDateSerializer.class)
	private Date businessAcceptanceDateTime;

	/**
	 * 手配業務担当者
	 */
	@Schema(description = "手配業務担当者", required = false, allowableValues = "range[0,255]")
	private String workUserName;

	/**
	 * 手配業務ステータス
	 */
	@Schema(description = "手配業務ステータス<br />" //
			+ "状態遷移上のワークフロー状態を表す。", //
			required = false, allowableValues = "受付待ち(\"1\"), 作業中(\"2\"), 作業完了報告(\"3\"), 承認依頼中(\"4\"), 作業完了(\"5\"), エラー(\"6\"), 破棄(\"7\")") //
	private ArrangementWork.WorkflowStatus arrangementWorkStatus;

	/**
	 * 見積書番号
	 */
	@Schema(description = "見積書番号", required = false, allowableValues = "range[0,18]")
	private String estimateNumber;

	/**
	 * 担当営業氏名
	 */
	@Schema(description = "担当営業氏名", required = false, allowableValues = "range[0,255]")
	private String picEmptxName;

	/**
	 * 担当支社
	 */
	@Schema(description = "担当支社", required = false, allowableValues = "range[0,255]")
	private String picAffiliateName;

	/**
	 * サービス開始日
	 */
	@Schema(description = "サービス開始日", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = UnixTimestampDateSerializer.class)
	private Date serviceTermStart;

	/**
	 * サービス終了日
	 */
	@Schema(description = "サービス終了日", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = UnixTimestampDateSerializer.class)
	private Date serviceTermEnd;

	/**
	 * 保留フラグ
	 */
	@Schema(description = "保留フラグ", required = true)
	private int holdingFlg;

	/**
	 * 手配ステータス
	 */
	@Schema(description = "手配ステータス<br />" //
			+ "状態遷移上のワークフロー状態を表す。", //
			required = false, allowableValues = "手配中(\"1\"), 手配完了(\"2\")") //
	private Arrangement.WorkflowStatus arrangementStatus;

	/**
	 * RJ管理番号
	 */
	@Schema(description = "RJ管理番号", required = false, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 恒久契約識別番号
	 */
	@Schema(description = "R恒久契約識別番号", required = false, allowableValues = "range[0,255]")
	private String immutableContIdentNumber;

	/**
	 * 受付担当SS組織
	 */
	@Schema(description = "受付担当SS組織", required = false, allowableValues = "range[0,255]")
	private String picAccSsName;

	/**
	 * 受付担当CE氏名
	 */
	@Schema(description = "受付担当CE氏名", required = false, allowableValues = "range[0,255]")
	private String picAccCeName;

	/**
	 * 導入担当SS組織
	 */
	@Schema(description = "導入担当SS組織", required = false, allowableValues = "range[0,255]")
	private String picIntSsName;

	/**
	 * 導入担当CE氏名
	 */
	@Schema(description = "導入担当CE氏名", required = false, allowableValues = "range[0,255]")
	private String picIntCeName;

	/**
	 * 保守担当SS組織
	 */
	@Schema(description = "保守担当SS組織", required = false, allowableValues = "range[0,255]")
	private String picMntSsName;

	/**
	 * 保守担当CE氏名
	 */
	@Schema(description = "保守担当CE氏名", required = false, allowableValues = "range[0,255]")
	private String picMntCeName;

	/**
	 * 登録日時
	 */
	@Schema(description = "登録日時", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = UnixTimestampDateSerializer.class)
	private Date createdAt;

	/**
	 * 更新日時
	 */
	@Schema(description = "更新日時", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = UnixTimestampDateSerializer.class)
	private Date updatedAt;

	/**
	 * 解約フラグ
	 */
	@Schema(description = "解約フラグ", required = true)
	private int disengagementFlg;

	/**
	 * 作業完了日時
	 */
	@Schema(description = "作業完了日時", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = UnixTimestampDateSerializer.class)
	private Date workCompletedAt;

	/**
	 * 手配業務タイプマスタID
	 */
	@Schema(description = "手配業務タイプマスタID", required = false)
	private long arrangementWorkTypeMasterId;

	/**
	 * お問い合わせ番号
	 */
	@Schema(description = "お問い合わせ番号", required = false, allowableValues = "range[0,255]")
	private String contactNo;

	/**
	 * 郵便番号
	 */
	@Schema(description = "郵便番号", required = false, allowableValues = "range[0,255]")
	private String postNumber;

	/**
	 * 都道府県
	 */
	@Schema(description = "都道府県", required = false, allowableValues = "range[0,255]")
	private String prefectures;

	/**
	 * 市区町村番地
	 */
	@Schema(description = "市区町村番地", required = false, allowableValues = "range[0,1000]")
	private String cityStreet;

	/**
	 * 建物名
	 */
	@Schema(description = "建物名", required = false, allowableValues = "range[0,255]")
	private String buildingName;

	/**
	 * 手配承認日時
	 */
	@Schema(description = "手配承認日時", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = UnixTimestampDateSerializer.class)
	private Date approvalDate;

	/**
	 * ベンダー管理番号
	 */
	@Schema(description = "ベンダー管理番号", required = false, allowableValues = "range[0,255]")
	private String vendorManageNumber;

	@PrePersist
	public void prePersist() {
		throw new IllegalAccessError();
	}
}