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
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.LifecycleStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.WorkflowStatus;
import jp.co.ricoh.cotos.commonlib.serializer.UnixTimestampDateSerializer;
import lombok.Data;

/**
 * 契約をリスト取得するためのDtoです。<br/>
 * 一覧を取得するためには、こちらのクラスを使ってください。 <br/>
 * このクラスを使用してDBへの保存を行うことは出来ません。
 */
@Entity
@Data
public class ContractListInfo {

	@Schema(description = "連番", requiredMode = Schema.RequiredMode.REQUIRED)
	private Long seqNo;

	@Id
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.REQUIRED)
	private long id;

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
	@Schema(description = "サービス識別番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,18]")
	private String serviceIdentificationNumber;

	/**
	 * 契約種別
	 */
	@Schema(description = "契約種別<br />" //
			+ "新規, 契約変更, 解約などの契約種別を表す。", //
			required = false, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")") //
	private ContractType contractType;

	/**
	 * 契約ステータス
	 */
	@Schema(description = "契約ステータス<br />" //
			+ "状態遷移上のワークフローステータスを表す。", //
			required = false, allowableValues = "作成中(\"1\"), 承認依頼中(\"2\"), 承認済(\"3\"), 業務依頼中(\"4\"), 業務処理完了(\"5\"), キャンセル申請中(\"6\"), 売上可能(\"7\"), 解約申請中(\"8\")") //
	private WorkflowStatus workflowStatus;

	/**
	 * 契約状態
	 */
	@Schema(description = "契約状態<br />" //
			+ "状態遷移上のライフサイクル状態を表す。", //
			required = false, allowableValues = "作成中(\"1\"), 作成完了(\"2\"), キャンセル手続き中(\"3\"), 破棄(\"4\"), 予定日待ち(\"5\"), 締結中(\"6\"), 解約手続き中(\"7\"), 解約予定日待ち(\"8\"), 解約(\"9\"), 旧契約(\"10\"), 締結待ち(\"11\")") //
	private LifecycleStatus lifecycleStatus;

	/**
	 * 見積ID
	 */
	@Schema(description = "見積ID", requiredMode = Schema.RequiredMode.REQUIRED)
	private long estimationId;

	/**
	 * 見積番号
	 */
	@Schema(description = "見積番号<br />" //
			+ "見積番号 + \"-\" + 見積番号枝番", //
			required = false, allowableValues = "range[0,18]") //
	private String estimateNumber;

	/**
	 * 見積件名
	 */
	@Schema(description = "見積件名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String estimationTitle;

	/**
	 * 案件番号
	 */
	@Schema(description = "案件番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String caseNumber;

	/**
	 * 請求開始月
	 */
	@Schema(description = "請求開始月", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String billingStartMonth;

	/**
	 * お客様企業名
	 */
	@Schema(description = "お客様企業名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * お客様事業所名
	 */
	@Schema(description = "お客様事業所名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String officeName;

	/**
	 * お客様部門名
	 */
	@Schema(description = "お客様部門名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String departmentName;

	/**
	 * サービス開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "サービス開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date serviceTermStart;

	/**
	 * サービス終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "サービス終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date serviceTermEnd;

	/**
	 * 商品名称
	 */
	@Schema(description = "商品名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String productName;

	/**
	 * 最終依頼審査承認履歴処理名
	 */
	@Schema(description = "最終依頼審査承認履歴処理名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "承認依頼(\"1\"), 承認依頼差戻(\"2\"), 承認(\"3\"), 承認依頼取消(\"4\"), 承認済差戻(\"5\")", example = "1")
	private ApprovalProcessCategory lastApprovalProcess;

	/**
	 * 担当営業氏名
	 */
	@Schema(description = "担当営業氏名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picEmptxName;

	/**
	 * 担当支社名
	 */
	@Schema(description = "担当支社名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picAffiliateName;

	/**
	 * 受付担当SS組織
	 */
	@Schema(description = "受付担当SS組織", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picAccSsName;

	/**
	 * 受付担当CE氏名
	 */
	@Schema(description = "受付担当CE氏名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picAccCeName;

	/**
	 * 導入担当SS組織
	 */
	@Schema(description = "導入担当SS組織", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picIntSsName;

	/**
	 * 導入担当CE氏名
	 */
	@Schema(description = "導入担当CE氏名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picIntCeName;

	/**
	 * 保守担当SS組織
	 */
	@Schema(description = "保守担当SS組織", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picMntSsName;

	/**
	 * 保守担当CE氏名
	 */
	@Schema(description = "保守担当CE氏名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String picMntCeName;

	/**
	 * RJ管理番号
	 */
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * WEB受注番号
	 */
	@Schema(description = "WEB受注番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String webOrderNumber;

	/**
	 * 恒久契約識別番号
	 */
	@Schema(description = "R恒久契約識別番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String immutableContIdentNumber;

	/**
	 * 上流システムコード
	 */
	@Schema(description = "上流システムコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String billingCustomerSpCode;

	/**
	 * MoM企業ID
	 */
	@Schema(description = "MoM企業ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String companyId;

	/**
	 * MoM企事部ID
	 */
	@Schema(description = "MoM企事部ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String momCustId;

	/**
	 * 商流区分
	 */
	@Schema(description = "商流区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String commercialFlowDiv;

	/**
	 * 支社コード
	 */
	@Schema(description = "支社コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ringsHanshCd;

	/**
	 * CUBIC部門コード
	 */
	@Schema(description = "CUBIC部門コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cubicOrgId;

	/**
	 * 部署
	 */
	@Schema(description = "部署", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String salesDepartmentName;

	/**
	 * RINGS社員コード
	 */
	@Schema(description = "RINGS社員コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ringsEmpCd;

	/**
	 * MOM社員ID
	 */
	@Schema(description = "MOM社員ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String momEmpId;

	/**
	 * 作成者
	 */
	@Schema(description = "作成者", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String createdUser;

	/**
	 * 課金開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "課金開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private Date billingStartDate;

	/**
	 * FFM発注問合せ番号
	 */
	@Schema(description = "FFM発注問合せ番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contactNo;

	/**
	 * 登録日時
	 */
	@Schema(description = "登録日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = UnixTimestampDateSerializer.class)
	private Date createdAt;

	/**
	 * 更新日時
	 */
	@Schema(description = "更新日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = UnixTimestampDateSerializer.class)
	private Date updatedAt;

	/**
	 * ベンダー管理番号
	 */
	@Schema(description = "ベンダー管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vendorManageNumber;

	/**
	 * 解約理由
	 */
	@Schema(description = "解約理由", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cancelReason;

	/**
	 * その他解約理由
	 */
	@Schema(description = "その他解約理由", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1000]")
	private String cancelReasonEtc;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String extendsParameter;

	/**
	 * 設置先企業ID
	 */
	@Schema(description = "設置先企業ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1000]")
	private String installationCompanyId;

	/**
	 * 設置先企業名
	 */
	@Schema(description = "設置先企業名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1000]")
	private String installationCompanyName;

	/**
	 * 設置先事業所ID
	 */
	@Schema(description = "設置先事業所ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1000]")
	private String installationOfficeId;

	/**
	 * 設置先事業所名
	 */
	@Schema(description = "設置先事業所名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1000]")
	private String installationOfficeName;

	/**
	 * 設置先住所
	 */
	@Schema(description = "設置先住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1000]")
	private String installationAddress;

	/**
	 * 設置先部門名
	 */
	@Schema(description = "設置先部門名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1000]")
	private String installationDepartmentName;

	/**
	 * 設置先企事部ID
	 */
	@Schema(description = "設置先企事部ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1000]")
	private String installationMomCustId;

	/**
	 * サービス利用希望日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "サービス利用希望日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date conclusionPreferredDate;

	/**
	 * 解約予定日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "解約予定日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date cancelScheduledDate;

	/**
	 * 管理拠点契約ID
	 */
	@Schema(description = "管理拠点契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String laitManageBaseContractId;

	@PrePersist
	public void prePersist() {
		throw new IllegalAccessError();
	}
}