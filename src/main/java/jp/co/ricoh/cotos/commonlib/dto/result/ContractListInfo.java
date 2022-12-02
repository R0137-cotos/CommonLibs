package jp.co.ricoh.cotos.commonlib.dto.result;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ApprovalProcessCategory;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ContractChangeTiming;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.LifecycleStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.WorkflowStatus;
import lombok.Data;

/**
 * 契約をリスト取得するためのDtoです。<br/>
 * 一覧を取得するためには、こちらのクラスを使ってください。 <br/>
 * このクラスを使用してDBへの保存を行うことは出来ません。
 */
@Entity
@Data
public class ContractListInfo {

	@Id
	@ApiModelProperty(value = "連番", required = true, position = 1)
	private long seqNo;

	@ApiModelProperty(value = "契約ID", required = true, position = 2)
	private long id;

	/**
	 * 契約番号
	 */
	@ApiModelProperty(value = "契約番号<br />" //
			+ "契約番号 + \"-\" + 契約番号枝番", //
			required = false, position = 3, allowableValues = "range[0,18]") //
	private String contractNumber;

	/**
	 * サービス識別番号
	 */
	@ApiModelProperty(value = "サービス識別番号", required = false, position = 4, allowableValues = "range[0,18]")
	private String serviceIdentificationNumber;

	/**
	 * 契約種別
	 */
	@ApiModelProperty(value = "契約種別<br />" //
			+ "新規, 契約変更, 解約などの契約種別を表す。", //
			required = false, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")", position = 5) //
	private ContractType contractType;

	/**
	 * 契約ステータス
	 */
	@ApiModelProperty(value = "契約ステータス<br />" //
			+ "状態遷移上のワークフローステータスを表す。", //
			required = false, allowableValues = "作成中(\"1\"), 承認依頼中(\"2\"), 承認済(\"3\"), 業務依頼中(\"4\"), 業務処理完了(\"5\"), キャンセル申請中(\"6\"), 売上可能(\"7\"), 解約申請中(\"8\")", position = 6) //
	private WorkflowStatus workflowStatus;

	/**
	 * 契約状態
	 */
	@ApiModelProperty(value = "契約状態<br />" //
			+ "状態遷移上のライフサイクル状態を表す。", //
			required = false, allowableValues = "作成中(\"1\"), 作成完了(\"2\"), キャンセル手続き中(\"3\"), 破棄(\"4\"), 予定日待ち(\"5\"), 締結中(\"6\"), 解約手続き中(\"7\"), 解約予定日待ち(\"8\"), 解約(\"9\"), 旧契約(\"10\"), 締結待ち(\"11\")", position = 7) //
	private LifecycleStatus lifecycleStatus;

	/**
	 * 見積ID
	 */
	@ApiModelProperty(value = "見積ID", required = true, position = 8)
	private long estimationId;

	/**
	 * 見積番号
	 */
	@ApiModelProperty(value = "見積番号<br />" //
			+ "見積番号 + \"-\" + 見積番号枝番", //
			required = false, position = 9, allowableValues = "range[0,18]") //
	private String estimateNumber;

	/**
	 * 見積件名
	 */
	@ApiModelProperty(value = "見積件名", required = false, position = 10, allowableValues = "range[0,255]")
	private String estimationTitle;

	/**
	 * 案件番号
	 */
	@ApiModelProperty(value = "案件番号", required = false, position = 11, allowableValues = "range[0,255]")
	private String caseNumber;

	/**
	 * 請求開始月
	 */
	@ApiModelProperty(value = "請求開始月", required = false, position = 12)
	private String billingStartMonth;

	/**
	 * お客様企業名
	 */
	@ApiModelProperty(value = "お客様企業名", required = false, position = 13, allowableValues = "range[0,255]")
	private String companyName;

	/**
	 * お客様事業所名
	 */
	@ApiModelProperty(value = "お客様事業所名", required = false, position = 14, allowableValues = "range[0,255]")
	private String officeName;

	/**
	 * お客様部門名
	 */
	@ApiModelProperty(value = "お客様部門名", required = false, position = 15, allowableValues = "range[0,255]")
	private String departmentName;

	/**
	 * サービス開始日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "サービス開始日", required = false, position = 16)
	private Date serviceTermStart;

	/**
	 * サービス終了日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "サービス終了日", required = false, position = 17)
	private Date serviceTermEnd;

	/**
	 * 商品名称
	 */
	@ApiModelProperty(value = "商品名称", required = false, position = 18, allowableValues = "range[0,255]")
	private String productName;

	/**
	 * 最終依頼審査承認履歴処理名
	 */
	@ApiModelProperty(value = "最終依頼審査承認履歴処理名", required = false, position = 19, allowableValues = "承認依頼(\"1\"), 承認依頼差戻(\"2\"), 承認(\"3\"), 承認依頼取消(\"4\"), 承認済差戻(\"5\")", example = "1")
	private ApprovalProcessCategory lastApprovalProcess;

	/**
	 * 担当営業氏名
	 */
	@ApiModelProperty(value = "担当営業氏名", required = false, position = 20, allowableValues = "range[0,255]")
	private String picEmptxName;

	/**
	 * 担当支社名
	 */
	@ApiModelProperty(value = "担当支社名", required = false, position = 21, allowableValues = "range[0,255]")
	private String picAffiliateName;

	/**
	 * 受付担当SS組織
	 */
	@ApiModelProperty(value = "受付担当SS組織", required = false, position = 22, allowableValues = "range[0,255]")
	private String picAccSsName;

	/**
	 * 受付担当CE氏名
	 */
	@ApiModelProperty(value = "受付担当CE氏名", required = false, position = 23, allowableValues = "range[0,255]")
	private String picAccCeName;

	/**
	 * 導入担当SS組織
	 */
	@ApiModelProperty(value = "導入担当SS組織", required = false, position = 24, allowableValues = "range[0,255]")
	private String picIntSsName;

	/**
	 * 導入担当CE氏名
	 */
	@ApiModelProperty(value = "導入担当CE氏名", required = false, position = 25, allowableValues = "range[0,255]")
	private String picIntCeName;

	/**
	 * 保守担当SS組織
	 */
	@ApiModelProperty(value = "保守担当SS組織", required = false, position = 26, allowableValues = "range[0,255]")
	private String picMntSsName;

	/**
	 * 保守担当CE氏名
	 */
	@ApiModelProperty(value = "保守担当CE氏名", required = false, position = 27, allowableValues = "range[0,255]")
	private String picMntCeName;

	/**
	 * RJ管理番号
	 */
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 28, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * WEB受注番号
	 */
	@ApiModelProperty(value = "WEB受注番号", required = false, position = 29, allowableValues = "range[0,255]")
	private String webOrderNumber;

	/**
	 * 恒久契約識別番号
	 */
	@ApiModelProperty(value = "R恒久契約識別番号", required = false, position = 30, allowableValues = "range[0,255]")
	private String immutableContIdentNumber;

	/**
	 * 上流システムコード
	 */
	@ApiModelProperty(value = "上流システムコード", required = false, position = 31, allowableValues = "range[0,255]")
	private String billingCustomerSpCode;

	/**
	 * MoM企業ID
	 */
	@ApiModelProperty(value = "MoM企業ID", required = false, position = 32, allowableValues = "range[0,255]")
	private String companyId;

	/**
	 * MoM企事部ID
	 */
	@ApiModelProperty(value = "MoM企事部ID", required = false, position = 33, allowableValues = "range[0,255]")
	private String momCustId;

	/**
	 * 商流区分
	 */
	@ApiModelProperty(value = "商流区分", required = false, position = 34, allowableValues = "range[0,255]")
	private String commercialFlowDiv;

	/**
	 * 支社コード
	 */
	@ApiModelProperty(value = "支社コード", required = false, position = 35, allowableValues = "range[0,255]")
	private String ringsHanshCd;

	/**
	 * CUBIC部門コード
	 */
	@ApiModelProperty(value = "CUBIC部門コード", required = false, position = 36, allowableValues = "range[0,255]")
	private String cubicOrgId;

	/**
	 * 部署
	 */
	@ApiModelProperty(value = "部署", required = false, position = 37, allowableValues = "range[0,255]")
	private String salesDepartmentName;

	/**
	 * RINGS社員コード
	 */
	@ApiModelProperty(value = "RINGS社員コード", required = false, position = 38, allowableValues = "range[0,255]")
	private String ringsEmpCd;

	/**
	 * MOM社員ID
	 */
	@ApiModelProperty(value = "MOM社員ID", required = false, position = 39, allowableValues = "range[0,255]")
	private String momEmpId;

	/**
	 * 作成者
	 */
	@ApiModelProperty(value = "作成者", required = false, position = 40, allowableValues = "range[0,255]")
	private String createdUser;

	/**
	 * 課金開始日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "課金開始日", required = false, position = 41, allowableValues = "range[0,255]")
	private Date billingStartDate;

	/**
	 * FFM発注問合せ番号
	 */
	@ApiModelProperty(value = "FFM発注問合せ番号", required = false, position = 42, allowableValues = "range[0,255]")
	private String contactNo;

	/**
	 * 登録日時
	 */
	@ApiModelProperty(value = "登録日時", required = false, position = 43)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	/**
	 * 更新日時
	 */
	@ApiModelProperty(value = "更新日時", required = false, position = 44)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	/**
	 * ベンダー管理番号
	 */
	@ApiModelProperty(value = "ベンダー管理番号", required = false, position = 45, allowableValues = "range[0,255]")
	private String vendorManageNumber;

	/**
	 * 解約理由
	 */
	@ApiModelProperty(value = "解約理由", required = false, position = 46, allowableValues = "range[0,255]")
	private String cancelReason;

	/**
	 * その他解約理由
	 */
	@ApiModelProperty(value = "その他解約理由", required = false, position = 47, allowableValues = "range[0,1000]")
	private String cancelReasonEtc;

	/**
	 * 拡張項目
	 */
	@ApiModelProperty(value = "拡張項目", required = false, position = 48)
	private String extendsParameter;

	/**
	 * 設置先企業ID
	 */
	@ApiModelProperty(value = "設置先企業ID", required = false, position = 49, allowableValues = "range[0,1000]")
	private String installationCompanyId;

	/**
	 * 設置先企業名
	 */
	@ApiModelProperty(value = "設置先企業名", required = false, position = 50, allowableValues = "range[0,1000]")
	private String installationCompanyName;

	/**
	 * 設置先事業所ID
	 */
	@ApiModelProperty(value = "設置先事業所ID", required = false, position = 51, allowableValues = "range[0,1000]")
	private String installationOfficeId;

	/**
	 * 設置先事業所名
	 */
	@ApiModelProperty(value = "設置先事業所名", required = false, position = 52, allowableValues = "range[0,1000]")
	private String installationOfficeName;

	/**
	 * 設置先住所
	 */
	@ApiModelProperty(value = "設置先住所", required = false, position = 53, allowableValues = "range[0,1000]")
	private String installationAddress;

	/**
	 * 設置先部門名
	 */
	@ApiModelProperty(value = "設置先部門名", required = false, position = 54, allowableValues = "range[0,1000]")
	private String installationDepartmentName;

	/**
	 * 設置先企事部ID
	 */
	@ApiModelProperty(value = "設置先企事部ID", required = false, position = 55, allowableValues = "range[0,1000]")
	private String installationMomCustId;

	/**
	 * サービス利用希望日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "サービス利用希望日", required = false, position = 56)
	private Date conclusionPreferredDate;

	/**
	 * 解約予定日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "解約予定日", required = false, position = 57)
	private Date cancelScheduledDate;

	/**
	 * 契約変更タイミング
	 */
	@ApiModelProperty(value = "契約変更タイミング", required = false, position = 58)
	private ContractChangeTiming contractChangeTiming;

	@PrePersist
	public void prePersist() {
		throw new IllegalAccessError();
	}
}