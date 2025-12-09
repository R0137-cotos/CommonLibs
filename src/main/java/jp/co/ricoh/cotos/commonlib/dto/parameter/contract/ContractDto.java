package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ContractChangeTiming;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ItemAddStatus;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.MvbAccountEntryDiv;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.RpaLinkageCpqUpdateCsvCreateStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.AbsConCsvCreateStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ArcsPeriodSaleMntOriginStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.BasicContractDiv;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractType;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.IfsLinkageCsvCreateStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.LifecycleStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.SaleDiv;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.SsWorkRequestCreateStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.WorkflowStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ContractDto extends DtoBase {

	/**
	 * 契約種別
	 */
	@NotNull
	@Schema(description = "契約種別", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")")
	private ContractType contractType;

	/**
	 * 商品グループマスタID
	 */
	@Min(0)
	@Schema(description = "商品グループマスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long productGrpMasterId;

	/**
	 * ライフサイクル状態
	 */
	@NotNull
	@Schema(description = "ライフサイクル状態", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "作成中(\"1\"), 作成完了(\"2\"), キャンセル手続き中(\"3\"), 破棄(\"4\"), 予定日待ち(\"5\"), 締結中(\"6\"), 解約手続き中(\"7\"), 解約予定日待ち(\"8\"), 解約(\"9\"), 旧契約(\"10\")", example = "1")
	private LifecycleStatus lifecycleStatus;

	/**
	 * ワークフロー状態
	 */
	@NotNull
	@Schema(description = "ワークフロー状態", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "作成中(\"1\"), 承認依頼中(\"2\"), 承認済(\"3\"), 業務依頼中(\"4\"), 業務処理完了(\"5\"), キャンセル申請中(\"6\"), 売上可能(\"7\"), 解約申請中(\"8\")", example = "1")
	private WorkflowStatus workflowStatus;

	/**
	 * 恒久契約識別番号
	 */
	@Size(max = 255)
	@Schema(description = "恒久契約識別番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String immutableContIdentNumber;

	/**
	 * 案件番号
	 */
	@Size(max = 255)
	@Schema(description = "案件番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String caseNumber;

	/**
	 * 案件名
	 */
	@Size(max = 255)
	@Schema(description = "案件名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String caseTitle;

	/**
	 * 契約番号
	 */
	@Size(max = 255)
	@NotNull
	@Schema(description = "契約番号", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@Min(0)
	@Max(99)
	@Schema(description = "契約番号枝番", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,99]")
	private int contractBranchNumber;

	/**
	 * 契約件名
	 */
	@Size(max = 255)
	@Schema(description = "契約件名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractTitle;

	/**
	 * 変更元契約番号
	 */
	@Size(max = 255)
	@Schema(description = "変更元契約番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String originContractNumber;

	/**
	 * 変更元契約番号枝番
	 */
	@Min(0)
	@Max(99)
	@Schema(description = "変更元契約番号枝番", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99]")
	private Integer originContractBranchNumber;

	/**
	 * 変更元契約ID
	 */
	@Min(0)
	@Schema(description = "変更元契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long originContractId;

	/**
	 * 変更希望日
	 */
	@Schema(description = "変更希望日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date changePreferredDate;

	/**
	 * 契約日
	 */
	@Schema(description = "契約日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date contractDate;

	/**
	 * 売上計上フラグ
	 */
	@Min(0)
	@Max(9)
	@Schema(description = "売上計上フラグ", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9]")
	private int accountSalesFlg;

	/**
	 * 請求開始日
	 */
	@Schema(description = "請求開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date billingDate;

	/**
	 * サービス開始日
	 */
	@Schema(description = "サービス開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date serviceTermStart;

	/**
	 * サービス終了日
	 */
	@Schema(description = "サービス終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date serviceTermEnd;

	/**
	 * 解約予定日
	 */
	@Schema(description = "解約予定日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date cancelScheduledDate;

	/**
	 * 見積番号
	 */
	@Size(max = 255)
	@Schema(description = "見積番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String estimationNumber;

	/**
	 * 見積番号枝番
	 */
	@Min(0)
	@Max(99)
	@Schema(description = "見積番号枝番", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99]")
	private Integer estimationBranchNumber;

	/**
	 * 見積ID
	 */
	@Min(0)
	@Schema(description = "見積ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long estimationId;

	/**
	 * 見積件名
	 */
	@Size(max = 255)
	@Schema(description = "見積件名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String estimationTitle;

	/**
	 * 商流区分
	 */
	@Size(max = 255)
	@Schema(description = "商流区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String commercialFlowDiv;

	/**
	 * 発行書式
	 */
	@Size(max = 255)
	@Schema(description = "発行書式", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String issueFormat;

	/**
	 * 得意先コード
	 */
	@Size(max = 255)
	@Schema(description = "得意先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String billingCustomerSpCode;

	/**
	 * 得意先宛先名
	 */
	@Size(max = 255)
	@Schema(description = "得意先宛先名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String billingCustomerSpName;

	/**
	 * 支払条件
	 */
	@Size(max = 255)
	@Schema(description = "支払条件", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String paymentTerms;

	/**
	 * 支払方法
	 */
	@Size(max = 255)
	@Schema(description = "支払方法", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String paymentMethod;

	/**
	 * 解約理由
	 */
	@Size(max = 255)
	@Schema(description = "解約理由", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cancelReason;

	/**
	 * その他解約理由
	 */
	@Size(max = 1000)
	@Schema(description = "その他解約理由", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1000]")
	private String cancelReasonEtc;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameter;

	/**
	 * web受注注文番号
	 */
	@Size(max = 255)
	@Schema(description = "web受注注文番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String webOrderNumber;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 課金開始日(ランニング)
	 */
	@Schema(description = "課金開始日(ランニング)")
	@Temporal(TemporalType.DATE)
	private Date billingStartDate;

	/**
	 * 解約注文番号
	 */
	@Size(max = 255)
	@Schema(description = "解約注文番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cancelOrderNo;

	/**
	 * サービス利用希望日
	 */
	@Schema(description = "サービス利用希望日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date conclusionPreferredDate;

	/**
	 * IFS連携用CSV作成状態
	 */
	@Schema(description = "IFS連携用CSV作成状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未作成(\"0\"), 作成済み(\"1\"), 作成対象外(\"2\"), 作成エラー(\"3\")")
	private IfsLinkageCsvCreateStatus ifsLinkageCsvCreateStatus;

	/**
	 * IFS連携用CSV作成日
	 */
	@Schema(description = "IFS連携用CSV作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date ifsLinkageCsvCreateDate;

	/**
	 * お問い合わせ番号
	 */
	@Size(max = 255)
	@Schema(description = "お問い合わせ番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contactNo;

	/**
	 * S&S作業依頼作成状態
	 */
	@Schema(description = "S&S作業依頼作成状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未作成(\"0\"),作成済み(\"1\"),作成エラー(\"2\")", example = "1")
	private SsWorkRequestCreateStatus ssWorkRequestCreateStatus;

	/**
	 * 帳票用消費税率区分
	 */
	@Size(max = 255)
	@Schema(description = "帳票用消費税率区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String issueTaxCodeValue;

	/**
	 * 申込日
	 */
	@Schema(description = "申込日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date applicationDate;

	/**
	 * 契約明細
	 */
	@Valid
	@NotNull
	@OneToMany(mappedBy = "contract")
	@Schema(description = "契約明細", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<ContractDetailDto> contractDetailList;

	/**
	 * 契約チェック結果
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "契約チェック結果", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ContractCheckResultDto> contractCheckResultList;

	/**
	 * 契約承認ルート
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "契約承認ルート", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ContractApprovalRouteDto> contractApprovalRouteList;

	/**
	 * 契約添付ファイル
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "契約添付ファイル", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ContractAttachedFileDto> contractAttachedFileList;

	/**
	 * 契約添付ファイル履歴
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "契約添付ファイル履歴", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ContractAttachedFileHistoryDto> contractAttachedFileHistoryList;

	/**
	 * 契約担当SA社員
	 */
	@Valid
	@NotNull
	@OneToOne(mappedBy = "contract")
	@Schema(description = "契約担当SA社員", requiredMode = Schema.RequiredMode.REQUIRED)
	private ContractPicSaEmpDto contractPicSaEmp;

	/**
	 * 契約追加編集者社員
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "契約追加編集者社員", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ContractAddedEditorEmpDto> contractAddedEditorEmpList;

	/**
	 * 販売店(契約用)
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "販売店(契約用)")
	private List<DealerContractDto> dealerContractList;

	/**
	 * 顧客(契約用)
	 */
	@Valid
	@NotNull
	@OneToOne(mappedBy = "contract")
	@Schema(description = "顧客(契約用)")
	private CustomerContractDto customerContract;

	/**
	 * 商品(契約用)
	 */
	@Valid
	@NotNull
	@OneToMany(mappedBy = "contract")
	@Schema(description = "商品(契約用)")
	private List<ProductContractDto> productContractList;

	/**
	 * 契約保守担当CE社員
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "契約保守担当CE社員", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ContractPicMntCeEmpDto contractPicMntCeEmp;

	/**
	 * 契約保守担当SS組織
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "契約保守担当SS組織", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ContractPicMntSsOrgDto contractPicMntSsOrg;

	/**
	 * 見積明細管理
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "見積明細管理", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ManagedEstimationDetailDto> managedEstimationDetailList;

	/**
	 * アプリケーションID
	 */
	@Size(max = 255)
	@Schema(description = "アプリケーションID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String appId;

	/**
	 * 契約自動更新日
	 */
	@Column
	@Schema(description = "契約自動更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date contractAutoUpdateDate;

	/**
	 * 届先コード
	 */
	@Size(max = 255)
	@Column
	@Schema(description = "届先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String deliveryCd;

	/**
	 * 届先名
	 */
	@Size(max = 255)
	@Column
	@Schema(description = "届先名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String deliveryName;

	/**
	 * 検収日
	 */
	@Column
	@Schema(description = "検収日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date acceptanceDate;

	/**
	 * 設置届先サイトID
	 */
	@Size(max = 255)
	@Schema(description = "設置届先サイトID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String installDeliverySiteId;

	/**
	 * 解約確定日
	 */
	@Column
	@Schema(description = "解約確定日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date cancelDecisionDate;

	/**
	 * 契約情報確定日
	 */
	@Column
	@Schema(description = "契約情報確定日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date fixedDate;

	/**
	 * 契約機種
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "契約機種", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ContractEquipmentDto> contractEquipmentList;

	/**
	 * 契約受付担当SS組織
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "契約受付担当SS組織", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ContractPicAccSsOrgDto contractPicAccSsOrg;

	/**
	 * 契約導入担当SS組織
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "契約導入担当SS組織", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ContractPicIntSsOrgDto contractPicIntSsOrg;

	/**
	 * 契約導入担当CE社員
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "契約導入担当CE社員", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ContractPicIntCeEmpDto contractPicIntCeEmp;

	/**
	 * 契約受付担当CE社員
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "契約受付担当CE社員", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ContractPicAccCeEmpDto contractPicAccCeEmp;

	/**
	 * 設置先(契約用)
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "設置先(契約用)")
	private ContractInstallationLocationDto contractInstallationLocation;

	/**
	 * 販売区分
	 */
	@Schema(description = "販売区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "訪問販売(\"1\"), Web販売(\"2\")")
	private SaleDiv saleDiv;

	/**
	 * ベンダー管理番号
	 */
	@Size(max = 255)
	@Schema(description = "ベンダー管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vendorManageNumber;

	/**
	 * 手動更新フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "手動更新フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer manualUpdateFlg;

	/**
	 * 品種追加状態
	 */
	@Schema(description = "品種追加状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未実施(\"0\"), 実施中(\"1\"), 実施済み(\"2\")")
	private ItemAddStatus itemAddStatus;

	/**
	 * 違約金用FFM発注問合せ番号
	 */
	@Size(max = 255)
	@Schema(description = "違約金用FFM発注問合せ番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String penaltyFfmOrderContactNo;

	/**
	 * 契約機種(Isys-Oneへの連携なし)
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "契約機種(Isys-Oneへの連携なし)")
	private List<ContractEquipmentNoIsysoneDto> contractEquipmentNoIsysoneList;

	/**
	 * 違約金明細(契約用)
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "違約金明細(契約用)")
	private List<PenaltyDetailContractDto> penaltyDetailContractList;

	/**
	 * 配送先
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "配送先", requiredMode = Schema.RequiredMode.REQUIRED)
	private ShippingAddressDto shippingAddress;

	/**
	 * 納品書・請求書印字用コメント <br>
	 * <br>
	 * 【仕入用管理Noの場合】 <br>
	 * 先頭3文字：商品マスタ.仕入先No、末尾9文字：契約情報画面（請求先情報セクション）より入力 <br>
	 * 【任意コメントの場合】 <br>
	 * 最大20Byteのコメント 契約情報画面（請求先情報セクション）より入力
	 *
	 */
	@Size(max = 255)
	@Schema(description = "納品書・請求書印字用コメント", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String purchaseManageNumber;

	/**
	 * 配送先SS組織
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "配送先SS組織", requiredMode = Schema.RequiredMode.REQUIRED)
	private ShippingAddressSsOrgDto shippingAddressSsOrg;

	/**
	 * ベンダー向けコメント
	 */
	@Size(max = 1333)
	@Schema(description = "ベンダー向けコメント", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1333]")
	private String toVendorComment;

	/**
	 * MVBアカウント
	 */
	@Size(max = 18)
	@Schema(description = "MVBアカウント", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,18]")
	private String mvbAccount;

	/**
	 * MVBアカウント登録区分
	 */
	@Schema(description = "MVBアカウント登録区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "新規登録(\"1\"), 既存使用(\"2\")")
	private MvbAccountEntryDiv mvbAccountEntryDiv;

	/**
	 * S&S作業依頼フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "S&S作業依頼フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer ssWorkRequestCreateFlg;

	/**
	 * 統合契約連携用CSV作成状態
	 */
	@Schema(description = "統合契約連携用CSV作成状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未作成(\"0\"), 作成済み(\"1\"),作成エラー(\"2\")")
	private AbsConCsvCreateStatus absConCsvCreateStatus;

	/**
	 * 統合契約連携用CSV作成日
	 */
	@Schema(description = "統合契約連携用CSV作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date absConCsvCreateDate;

	/**
	 * 統合契約連携用CSV作成状態(解約)
	 */
	@Schema(description = "統合契約連携用CSV作成状態(解約)", allowableValues = "未作成(\"0\"), 作成済み(\"1\"),作成エラー(\"2\")")
	private AbsConCsvCreateStatus absConCsvCreateStatusCancel;

	/**
	 * 統合契約連携用CSV作成日(解約)
	 */
	@Schema(description = "統合契約連携用CSV作成日(解約)")
	@Temporal(TemporalType.DATE)
	private Date absConCsvCreateDateCancel;

	/**
	 * ARCS期間売保守元契約処理状態
	 */
	@Schema(description = "ARCS期間売保守元契約処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未作成(\"0\"),CSV作成済み(\"1\"),対象外(\"2\"),管理対象外(\"3\")")
	private ArcsPeriodSaleMntOriginStatus arcsPeriodSaleMntOriginStatus;

	/**
	 * ARCS期間売保守元契約連携日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "ARCS期間売保守元契約連携日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date arcsPeriodSaleMntOriginLinkAt;

	/**
	 * IFS連携用解約CSV作成状態
	 */
	@Schema(description = "IFS連携用解約CSV作成状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未作成(\"0\"), 作成済み(\"1\"), 作成対象外(\"2\"), 作成エラー(\"3\")")
	private IfsLinkageCsvCreateStatus ifsLinkageCancelCsvStatus;

	/**
	 * IFS連携用解約CSV作成日
	 */
	@Schema(description = "IFS連携用解約CSV作成日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date ifsLinkageCancelCsvDate;

	/**
	 * 次回契約更新可能フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "次回契約更新可能フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer nextUpdatePossibleFlg;

	/**
	 * 次回自動更新フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "次回自動更新フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer nextAutoUpdateFlg;

	/**
	 * 次回継続可能機種なしフラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "次回継続可能機種なしフラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer nextEquipmentNotContFlg;

	/**
	 * 基本契約区分
	 */
	@Schema(description = "基本契約区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "基本契約(\"1\"),基本契約_一部(\"2\")")
	private BasicContractDiv basicContractDiv;

	/**
	 * 基本契約ID
	 */
	@Min(0)
	@Schema(description = "基本契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long basicContractId;

	/**
	 * 納入日
	 */
	@Schema(description = "納入日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date deliveryDate;

	/**
	 * サービス終了最大延長日
	 */
	@Schema(description = "サービス終了最大延長日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date serviceTermMaxEnd;

	/**
	 * 延長可能契約月数
	 */
	@Schema(description = "延長可能契約月数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long maxExtensionMonths;

	/**
	 * 契約変更タイミング
	 */
	@Schema(description = "契約変更タイミング", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ContractChangeTiming contractChangeTiming;

	/**
	 * NTTお客様ID
	 */
	@Size(max = 255)
	@Schema(description = "NTTお客様ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String nttCustomerId;

	/**
	 * 回収先
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "回収先", requiredMode = Schema.RequiredMode.REQUIRED)
	private CollectLocationDto collectLocation;

	/**
	 * RPA連携用CPQ更新CSV作成状態
	 */
	@Schema(description = "RPA連携用CPQ更新CSV作成状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "連携済み(\"1\"),対象外(\"2\")")
	private RpaLinkageCpqUpdateCsvCreateStatus rpaLinkageCpqUpdateCsvCreateStatus;

	/**
	 * RPA連携用CPQ更新CSV作成日
	 */
	@Schema(description = "RPA連携用CPQ更新CSV作成日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date rpaLinkageCpqUpdateCsvCreateAt;

	/**
	 * V-UP見積番号
	 */
	@Size(max = 255)
	@Schema(description = "V-UP見積番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vupEstimationNumber;

	/**
	 * 機器情報取込不要フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "機器情報取込不要フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer equipmentImportUnnecessaryFlg;

	/**
	 * 振替情報取込不要フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "振替情報取込不要フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer transImportUnnecessaryFlg;

	/**
	 * 契約機種付加情報
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "契約機種付加情報", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ContractEquipmentAdditionInfoDto> contractEquipmentAdditionInfoList;

	/**
	 * 契約機種状態管理
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "契約機種状態管理", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ManagedContractEquipmentStatusDto> managedContractEquipmentStatusList;

	/**
	 * 電子契約正式帳票名
	 */
	@Size(max = 255)
	@Schema(description = "電子契約正式帳票名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String electronicContractOfficialReportName;

	/**
	 * 電子契約帳票EIMアップロードフラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "電子契約帳票EIMアップロードフラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer electronicContractReportEimUploadFlg;

	/**
	 * 電子契約締結日
	 */
	@Schema(description = "電子契約締結日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date electronicContractConclusionDate;
}
