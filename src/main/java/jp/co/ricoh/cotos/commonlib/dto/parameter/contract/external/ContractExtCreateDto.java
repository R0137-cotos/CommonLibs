package jp.co.ricoh.cotos.commonlib.dto.parameter.contract.external;

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
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractAddedEditorEmpDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractDetailDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractEquipmentAdditionInfoDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractInstallationLocationDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractPicAccCeEmpDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractPicAccSsOrgDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractPicIntCeEmpDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractPicIntSsOrgDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractPicMntCeEmpDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractPicMntSsOrgDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ContractPicSaEmpDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.CustomerContractDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.DealerContractDto;
import jp.co.ricoh.cotos.commonlib.dto.parameter.contract.ManagedEstimationDetailDto;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.BasicContractDiv;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.IfsLinkageCsvCreateStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.LifecycleStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.SsWorkRequestCreateStatus;
import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.WorkflowStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class ContractExtCreateDto extends DtoBase {

	/**
	 * 商品グループマスタID
	 */
	@Min(0)
	@Schema(description = "商品グループマスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long productGrpMasterId;

	/**
	 * ライフサイクル状態
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "ライフサイクル状態", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "作成中(\"1\"), 作成完了(\"2\"), キャンセル手続き中(\"3\"), 破棄(\"4\"), 予定日待ち(\"5\"), 締結中(\"6\"), 解約手続き中(\"7\"), 解約予定日待ち(\"8\"), 解約(\"9\"), 旧契約(\"10\")", example = "1")
	private LifecycleStatus lifecycleStatus;

	/**
	 * ワークフロー状態
	 */
	@Column(nullable = false)
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
	@Schema(description = "案件番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	@Size(max = 255)
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
	@Schema(description = "契約番号", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String contractNumber;

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
	@Max(99)
	@Min(0)
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
	@Max(99)
	@Min(0)
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
	@Schema(description = "得意先コード<br/>※POST時「MoM請求売上先サイト情報マスタ」存在チェック実施", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
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
	@NotNull
	@Schema(description = "web受注注文番号", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String webOrderNumber;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 課金開始日
	 */
	@Schema(description = "課金開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date billingStartDate;

	/**
	 * 解約注文番号
	 */
	@Size(max = 255)
	@Schema(description = "解約注文番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cancelOrderNo;

	/**
	 * 締結希望日
	 */
	@Schema(description = "締結希望日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
	 * 設置届先サイトID
	 */
	@Size(max = 255)
	@Schema(description = "設置届先サイトID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String installDeliverySiteId;

	/**
	 * ベンダー管理番号
	 */
	@Size(max = 255)
	@Column
	@Schema(description = "ベンダー管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vendorManageNumber;

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
	@OneToMany(mappedBy = "contract")
	@Schema(description = "契約明細", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ContractDetailDto> contractDetailList;

	/**
	 * 契約担当SA社員
	 */
	@Valid
	@NotNull
	@OneToOne(mappedBy = "contract")
	@Schema(description = "契約担当SA社員", requiredMode = Schema.RequiredMode.REQUIRED)
	private ContractPicSaEmpDto contractPicSaEmp;

	/**
	 * 販売店(契約用)
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "販売店(契約用)", required = false)
	private List<DealerContractDto> dealerContractList;

	/**
	 * 顧客(契約用)
	 */
	@Valid
	@NotNull
	@OneToOne(mappedBy = "contract")
	@Schema(description = "顧客(契約用)", required = true)
	private CustomerContractDto customerContract;

	/**
	 * 商品(契約用)
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "商品(契約用)", required = true)
	private List<ProductContractExtCreateDto> productContractList;

	/**
	 * 見積明細管理
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "見積明細管理", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ManagedEstimationDetailDto> managedEstimationDetailList;

	/**
	 * 追加編集者
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "追加編集者", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ContractAddedEditorEmpDto> contractAddedEditorEmpList;

	/**
	 * 設置先(契約用)
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "設置先(契約用)", required = false)
	private ContractInstallationLocationDto contractInstallationLocation;

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
	 * 契約受付担当SS組織
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "契約受付担当SS組織", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ContractPicAccSsOrgDto contractPicAccSsOrg;

	/**
	 * 契約受付担当CE社員
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "契約受付担当CE社員", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ContractPicAccCeEmpDto contractPicAccCeEmp;

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
	 * 契約保守担当SS組織
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "契約保守担当SS組織", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ContractPicMntSsOrgDto contractPicMntSsOrg;

	/**
	 * 契約保守担当CE社員
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "契約保守担当CE社員", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ContractPicMntCeEmpDto contractPicMntCeEmp;

	/**
	 * S&S作業依頼作成状態
	 */
	@Schema(description = "S&S作業依頼作成状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未作成(\"0\"),作成済み(\"1\"),作成エラー(\"2\")")
	private SsWorkRequestCreateStatus ssWorkRequestCreateStatus;

	/**
	 * S&S作業依頼フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "S&S作業依頼フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer ssWorkRequestCreateFlg;

	/**
	 * 契約機種付加情報
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "契約機種付加情報", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ContractEquipmentAdditionInfoDto> contractEquipmentAdditionInfoList;
}
