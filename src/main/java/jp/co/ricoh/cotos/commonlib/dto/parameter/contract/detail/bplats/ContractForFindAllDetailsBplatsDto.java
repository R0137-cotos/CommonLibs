package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.detail.bplats;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ItemAddStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.IfsLinkageCsvCreateStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.LifecycleStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.SaleDiv;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.SsWorkRequestCreateStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.WorkflowStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAddedEditorEmp;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractApprovalRoute;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAttachedFile;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractAttachedFileHistory;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractCheckResult;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractEquipment;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractInstallationLocation;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractOperationLog;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractPicAccCeEmp;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractPicAccSsOrg;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractPicIntCeEmp;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractPicIntSsOrg;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractPicMntCeEmp;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractPicMntSsOrg;
import jp.co.ricoh.cotos.commonlib.entity.contract.ContractPicSaEmp;
import jp.co.ricoh.cotos.commonlib.entity.contract.CustomerContract;
import jp.co.ricoh.cotos.commonlib.entity.contract.DealerContract;
import jp.co.ricoh.cotos.commonlib.entity.contract.ManagedEstimationDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約鑑を表す契約一覧情報詳細取得API用DTO
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class ContractForFindAllDetailsBplatsDto extends EntityBase {

	/**
	 * ID
	 */
	@Min(0)
	@Schema(description = "契約ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約種別
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "契約種別", required = true, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")")
	private ContractType contractType;

	/**
	 * 商品グループマスタID
	 */
	@Min(0)
	@Schema(description = "商品グループマスタID", required = false, allowableValues = "range[0,9223372036854775807]")
	private long productGrpMasterId;

	/**
	 * ライフサイクル状態
	 */
	@Column(nullable = false)
	@Schema(description = "ライフサイクル状態(作成時不要)", required = true, allowableValues = "作成中(\"1\"), 作成完了(\"2\"), キャンセル手続き中(\"3\"), 破棄(\"4\"), 予定日待ち(\"5\"), 締結中(\"6\"), 解約手続き中(\"7\"), 解約予定日待ち(\"8\"), 解約(\"9\"), 旧契約(\"10\")", example = "1", readOnly = true)
	private LifecycleStatus lifecycleStatus;

	/**
	 * ワークフロー状態
	 */
	@Column(nullable = false)
	@Schema(description = "ワークフロー状態(作成時不要)", required = true, allowableValues = "作成中(\"1\"), 承認依頼中(\"2\"), 承認済(\"3\"), 業務依頼中(\"4\"), 業務処理完了(\"5\"), キャンセル申請中(\"6\"), 売上可能(\"7\"), 解約申請中(\"8\")", example = "1", readOnly = true)
	private WorkflowStatus workflowStatus;

	/**
	 * 恒久契約識別番号
	 */
	@Schema(description = "恒久契約識別番号(作成時不要)", required = false, allowableValues = "range[0,255]", readOnly = true)
	private String immutableContIdentNumber;

	/**
	 * 案件番号
	 */
	@Schema(description = "案件番号", required = false, allowableValues = "range[0,255]")
	@Size(max = 255)
	private String caseNumber;

	/**
	 * 案件名
	 */
	@Size(max = 255)
	@Schema(description = "案件名", required = false, allowableValues = "range[0,255]")
	private String caseTitle;

	/**
	 * 契約番号
	 */
	@Column(nullable = false)
	@Schema(description = "契約番号(作成時不要)", required = true, allowableValues = "range[0,255]", readOnly = true)
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@Column(nullable = false)
	@Schema(description = "契約番号枝番(作成時不要)", required = true, allowableValues = "range[0,99]", readOnly = true)
	private int contractBranchNumber;

	/**
	 * 契約件名
	 */
	@Size(max = 255)
	@Schema(description = "契約件名", required = false, allowableValues = "range[0,255]")
	private String contractTitle;

	/**
	 * 変更元契約番号
	 */
	@Size(max = 255)
	@Schema(description = "変更元契約番号", required = false, allowableValues = "range[0,255]")
	private String originContractNumber;

	/**
	 * 変更元契約番号枝番
	 */
	@Max(99)
	@Min(0)
	@Schema(description = "変更元契約番号枝番", required = false, allowableValues = "range[0,99]")
	private Integer originContractBranchNumber;

	/**
	 * 変更元契約ID
	 */
	@Min(0)
	@Schema(description = "変更元契約ID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long originContractId;

	/**
	 * 変更希望日
	 */
	@Schema(description = "変更希望日", required = false)
	@Temporal(TemporalType.DATE)
	private Date changePreferredDate;

	/**
	 * 契約日
	 */
	@Schema(description = "契約日", required = false)
	@Temporal(TemporalType.DATE)
	private Date contractDate;

	/**
	 * 売上計上フラグ
	 */
	@Column(nullable = false)
	@Schema(description = "売上計上フラグ(作成時不要)", required = true, allowableValues = "range[0,9]", readOnly = true)
	private int accountSalesFlg;

	/**
	 * 請求開始日
	 */
	@Schema(description = "請求開始日", required = false)
	@Temporal(TemporalType.DATE)
	private Date billingDate;

	/**
	 * サービス開始日
	 */
	@Schema(description = "サービス開始日", required = false)
	@Temporal(TemporalType.DATE)
	private Date serviceTermStart;

	/**
	 * サービス終了日
	 */
	@Schema(description = "サービス終了日", required = false)
	@Temporal(TemporalType.DATE)
	private Date serviceTermEnd;

	/**
	 * 解約予定日
	 */
	@Schema(description = "解約予定日", required = false)
	@Temporal(TemporalType.DATE)
	private Date cancelScheduledDate;

	/**
	 * 見積番号
	 */
	@Size(max = 255)
	@Schema(description = "見積番号", required = false, allowableValues = "range[0,255]")
	private String estimationNumber;

	/**
	 * 見積番号枝番
	 */
	@Max(99)
	@Min(0)
	@Schema(description = "見積番号枝番", required = false, allowableValues = "range[0,99]")
	private Integer estimationBranchNumber;

	/**
	 * 見積ID
	 */
	@Min(0)
	@Schema(description = "見積ID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long estimationId;

	/**
	 * 見積件名
	 */
	@Size(max = 255)
	@Schema(description = "見積件名", required = false, allowableValues = "range[0,255]")
	private String estimationTitle;

	/**
	 * 商流区分
	 */
	@Size(max = 255)
	@Schema(description = "商流区分", required = false, allowableValues = "range[0,255]")
	private String commercialFlowDiv;

	/**
	 * 発行書式
	 */
	@Size(max = 255)
	@Schema(description = "発行書式", required = false, allowableValues = "range[0,255]")
	private String issueFormat;

	/**
	 * 得意先コード
	 */
	@Size(max = 255)
	@Schema(description = "得意先コード<br/>※POST時「MoM請求売上先サイト情報マスタ」存在チェック実施", required = false, allowableValues = "range[0,255]")
	private String billingCustomerSpCode;

	/**
	 * 得意先宛先名
	 */
	@Size(max = 255)
	@Schema(description = "得意先宛先名", required = false, allowableValues = "range[0,255]")
	private String billingCustomerSpName;

	/**
	 * 支払条件
	 */
	@Size(max = 255)
	@Schema(description = "支払条件", required = false, allowableValues = "range[0,255]")
	private String paymentTerms;

	/**
	 * 支払方法
	 */
	@Size(max = 255)
	@Schema(description = "支払方法", required = false, allowableValues = "range[0,255]")
	private String paymentMethod;

	/**
	 * 解約理由
	 */
	@Size(max = 255)
	@Schema(description = "解約理由", required = false, allowableValues = "range[0,255]")
	private String cancelReason;

	/**
	 * その他解約理由
	 */
	@Size(max = 1000)
	@Schema(description = "その他解約理由", required = false, allowableValues = "range[0,1000]")
	private String cancelReasonEtc;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", required = false)
	@Lob
	private String extendsParameter;

	/**
	 * web受注注文番号
	 */
	@Size(max = 255)
	@Schema(description = "web受注注文番号", required = false, allowableValues = "range[0,255]")
	private String webOrderNumber;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", required = false, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 課金開始日(ランニング)
	 */
	@Schema(description = "課金開始日(ランニング)", required = false)
	@Temporal(TemporalType.DATE)
	private Date billingStartDate;

	/**
	 * 解約注文番号
	 */
	@Size(max = 255)
	@Schema(description = "解約注文番号", required = false, allowableValues = "range[0,255]")
	private String cancelOrderNo;

	/**
	 * サービス利用希望日
	 */
	@Schema(description = "サービス利用希望日", required = false)
	@Temporal(TemporalType.DATE)
	private Date conclusionPreferredDate;

	/**
	 * IFS連携用CSV作成状態
	 */
	@Schema(description = "IFS連携用CSV作成状態", required = false, allowableValues = "未作成(\"0\"), 作成済み(\"1\"), 作成対象外(\"2\"), 作成エラー(\"3\")")
	private IfsLinkageCsvCreateStatus ifsLinkageCsvCreateStatus;

	/**
	 * IFS連携用CSV作成日
	 */
	@Schema(description = "IFS連携用CSV作成日", required = false)
	@Temporal(TemporalType.DATE)
	private Date ifsLinkageCsvCreateDate;

	/**
	 * お問い合わせ番号
	 */
	@Size(max = 255)
	@Schema(description = "お問い合わせ番号", required = false, allowableValues = "range[0,255]")
	private String contactNo;

	/**
	 * S&S作業依頼作成状態
	 */
	@Schema(description = "S&S作業依頼作成状態", required = false, allowableValues = "未作成(\"0\"),作成済み(\"1\"),作成エラー(\"2\")")
	private SsWorkRequestCreateStatus ssWorkRequestCreateStatus;

	/**
	 * 帳票用消費税率区分
	 */
	@Size(max = 255)
	@Schema(description = "帳票用消費税率区分", required = false, allowableValues = "range[0,255]")
	private String issueTaxCodeValue;

	/**
	 * 申込日
	 */
	@Schema(description = "申込日", required = false)
	@Temporal(TemporalType.DATE)
	private Date applicationDate;

	/**
	 * 契約明細
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "契約明細", required = true)
	private List<ContractDetailForFindAllDetailsBplatsDto> contractDetailList;

	/**
	 * 契約チェック結果
	 */
	@OneToMany(mappedBy = "contract")
	@OrderBy("displayOrder ASC")
	@Schema(description = "契約チェック結果(作成時不要)", required = false, readOnly = true)
	private List<ContractCheckResult> contractCheckResultList;

	/**
	 * 契約承認ルート
	 */
	@OneToMany(mappedBy = "contract")
	@Schema(description = "契約承認ルート(作成時不要)", required = false, readOnly = true)
	private List<ContractApprovalRoute> contractApprovalRouteList;

	/**
	 * 契約添付ファイル
	 */
	@OneToMany(mappedBy = "contract")
	@Schema(description = "契約添付ファイル(作成時不要)", required = false, readOnly = true)
	private List<ContractAttachedFile> contractAttachedFileList;

	/**
	 * 契約担当SA社員
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "契約担当SA社員", required = true)
	private ContractPicSaEmp contractPicSaEmp;

	/**
	 * 契約追加編集者社員
	 */
	@OneToMany(mappedBy = "contract")
	@Schema(description = "契約追加編集者社員(作成時不要)", required = false, readOnly = true)
	private List<ContractAddedEditorEmp> contractAddedEditorEmpList;

	/**
	 * 販売店(契約用)
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "販売店(契約用)", required = false)
	private List<DealerContract> dealerContractList;

	/**
	 * 顧客(契約用)
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "顧客(契約用)", required = true)
	private CustomerContract customerContract;

	/**
	 * 契約操作履歴
	 */
	@OneToMany(mappedBy = "contract")
	@OrderBy("operatedAt ASC")
	@Schema(description = "契約操作履歴(作成時不要)", required = true, readOnly = true)
	private List<ContractOperationLog> contractOperationLogList;

	/**
	 * 商品(契約用)
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "商品(契約用)", required = true)
	private List<ProductContractForFindAllDetailsBplatsDto> productContractList;

	/**
	 * 契約保守担当CE社員
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "契約保守担当CE社員(作成時不要)", required = false, readOnly = true)
	private ContractPicMntCeEmp contractPicMntCeEmp;

	/**
	 * 契約保守担当SS組織
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "契約保守担当SS組織(作成時不要)", required = false, readOnly = true)
	private ContractPicMntSsOrg contractPicMntSsOrg;

	/**
	 * 契約受付担当SS組織
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "契約受付担当SS組織(作成時不要)", required = false, readOnly = true)
	private ContractPicAccSsOrg contractPicAccSsOrg;

	/**
	 * 契約導入担当SS組織
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "契約導入担当SS組織(作成時不要)", required = false, readOnly = true)
	private ContractPicIntSsOrg contractPicIntSsOrg;

	/**
	 * 契約添付ファイル履歴
	 */
	@OneToMany(mappedBy = "contract")
	@Schema(description = "契約添付ファイル履歴(作成時不要)", required = true)
	private List<ContractAttachedFileHistory> contractAttachedFileHistoryList;

	/**
	 * 契約受付担当CE社員
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "契約受付担当CE社員(作成時不要)", required = false, readOnly = true)
	private ContractPicAccCeEmp contractPicAccCeEmp;

	/**
	 * 契約導入担当CE社員
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "契約導入担当CE社員(作成時不要)", required = false, readOnly = true)
	private ContractPicIntCeEmp contractPicIntCeEmp;

	/**
	 * 契約機種
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "契約機種(作成時不要)", required = false, readOnly = true)
	private List<ContractEquipment> contractEquipmentList;

	/**
	 * 見積明細管理
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "見積明細管理", required = true)
	private List<ManagedEstimationDetail> managedEstimationDetailList;

	/**
	 * 設置先(契約用)
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "設置先(契約用)", required = true)
	private ContractInstallationLocation contractInstallationLocation;

	/**
	 * アプリケーションID
	 */
	@Size(max = 255)
	@Schema(description = "アプリケーションID", required = false, allowableValues = "range[0,255]")
	private String appId;

	/**
	 * 契約自動更新日
	 */
	@Column
	@Schema(description = "契約自動更新日", required = false)
	private Date contractAutoUpdateDate;

	/**
	 * 届先コード
	 */
	@Column
	@Schema(description = "届先コード", required = false, allowableValues = "range[0,]")
	private String deliveryCd;

	/**
	 * 届先名
	 */
	@Column
	@Schema(description = "届先名", required = false, allowableValues = "range[0,]")
	private String deliveryName;

	/**
	 * 検収日
	 */
	@Column
	@Schema(description = "検収日", required = false)
	private Date acceptanceDate;

	/**
	 * 設置届先サイトID
	 */
	@Size(max = 255)
	@Schema(description = "設置届先サイトID", required = false, allowableValues = "range[0,255]")
	private String installDeliverySiteId;

	/**
	 * 販売区分
	 */
	@Schema(description = "販売区分", required = false, allowableValues = "訪問販売(\"1\"), Web販売(\"2\")")
	private SaleDiv saleDiv;

	/**
	 * ベンダー管理番号
	 */
	@Size(max = 255)
	@Schema(description = "ベンダー管理番号", required = false, allowableValues = "range[0,255]")
	private String vendorManageNumber;

	/**
	 * 手動更新フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "手動更新フラグ", required = false, allowableValues = "range[0,9]")
	private Integer manualUpdateFlg;

	/**
	 * 品種追加状態
	 */
	@Schema(description = "品種追加状態", required = false, allowableValues = "未実施(\"0\"), 実施中(\"1\"), 実施済み(\"2\")")
	private ItemAddStatus itemAddStatus;

	/**
	 * 商品グループコード
	 */
	@Size(max = 255)
	@Schema(description = "商品グループコード", required = false, allowableValues = "range[0,255]")
	private String productGroupCd;
}
