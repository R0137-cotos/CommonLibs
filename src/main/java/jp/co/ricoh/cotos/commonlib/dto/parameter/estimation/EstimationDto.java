package jp.co.ricoh.cotos.commonlib.dto.parameter.estimation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ContractChangeTiming;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ItemAddStatus;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation.ElectronicContractLinkageStatus;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation.EstimationType;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation.LifecycleStatus;
import jp.co.ricoh.cotos.commonlib.entity.estimation.Estimation.WorkflowStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class EstimationDto extends DtoBase {

	/**
	 * 商品グループマスタID
	 */
	@Min(0)
	@Schema(description = "商品グループマスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long productGrpMasterId;

	/**
	 * ライフサイクル状態
	 */
	@NotNull
	@Schema(description = "ライフサイクル状態", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "作成中(\"1\"), 作成完了(\"2\"), 受注(\"3\"), 失注(\"4\"), 破棄(\"5\")", example = "1")
	private LifecycleStatus lifecycleStatus;

	/**
	 * ワークフロー状態
	 */
	@NotNull
	@Schema(description = "ワークフロー状態", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "作成中(\"1\"), 業務依頼中(\"2\"), 業務処理完了(\"3\"), 承認依頼中(\"4\"), 承認済(\"5\"), 顧客提示済(\"6\")", example = "1")
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
	 * 見積番号
	 */
	@NotNull
	@Size(max = 255)
	@Schema(description = "見積番号", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String estimationNumber;

	/**
	 * 見積番号枝番
	 */
	@Min(0)
	@Max(99)
	@Schema(description = "見積番号枝番", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,99]")
	private int estimationBranchNumber;

	/**
	 * 見積件名
	 */
	@Size(max = 255)
	@Schema(description = "見積件名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String estimationTitle;

	/**
	 * 見積種別
	 */
	@NotNull
	@Schema(description = "見積種別", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "新規(\"1\"), 契約変更(\"2\")", example = "1")
	private EstimationType estimationType;

	/**
	 * 見積作成元システム区分
	 */
	@Size(max = 255)
	@Schema(description = "見積作成元システム区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String estimatedSystemDiv;

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
	 * 帳票用見積件名
	 */
	@Size(max = 255)
	@Schema(description = "帳票用見積件名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String issueEstimationTitle;

	/**
	 * 帳票用顧客企業名
	 */
	@Size(max = 255)
	@Schema(description = "帳票用顧客企業名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String issueCustomerCorpName;

	/**
	 * 見積有効期限
	 */
	@Schema(description = "見積有効期限", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date estimationLimit;

	/**
	 * 見積鑑用企業名
	 */
	@Size(max = 255)
	@Schema(description = "見積鑑用企業名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String coverCompanyName;

	/**
	 * 見積鑑用敬称
	 */
	@Size(max = 255)
	@Schema(description = "見積鑑用敬称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String coverTitle;

	/**
	 * 見積鑑用見積件名
	 */
	@Size(max = 255)
	@Schema(description = "見積鑑用見積件名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String coverEstimationSubject;

	/**
	 * 見積鑑用支払条件
	 */
	@Size(max = 255)
	@Schema(description = "見積鑑用支払条件", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String coverPaymentTerms;

	/**
	 * 見積鑑用納期
	 */
	@Schema(description = "見積鑑用納期", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date coverDeliveryDate;

	/**
	 * 見積鑑用有効期限
	 */
	@Schema(description = "見積鑑用有効期限", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date coverExpirationDate;

	/**
	 * 見積鑑用備考
	 */
	@Size(max = 255)
	@Schema(description = "見積鑑用備考", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String coverRemarks;

	/**
	 * 見積鑑用見積提示日
	 */
	@Schema(description = "見積鑑用見積提示日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date coverPresentationDate;

	/**
	 * 見積発行元会社名
	 */
	@Size(max = 255)
	@Schema(description = "見積発行元会社名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String publishCompany;

	/**
	 * 見積発行元所属
	 */
	@Size(max = 255)
	@Schema(description = "見積発行元所属", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String publishDepartment;

	/**
	 * 見積発行元郵便番号
	 */
	@Size(max = 255)
	@Schema(description = "見積発行元郵便番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String publishPostNumber;

	/**
	 * 見積発行元住所
	 */
	@Size(max = 1000)
	@Schema(description = "見積発行元住所", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,1000]")
	private String publishAddress;

	/**
	 * 見積発行元電話番号
	 */
	@Size(max = 255)
	@Schema(description = "見積発行元電話番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String publishTel;

	/**
	 * 見積発行元FAX番号
	 */
	@Size(max = 255)
	@Schema(description = "見積発行元FAX番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String publishFax;

	/**
	 * 見積発行元担当者名
	 */
	@Size(max = 255)
	@Schema(description = "見積発行元担当者名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String publishEmployee;

	/**
	 * 特価希望理由
	 */
	@Size(max = 255)
	@Schema(description = "特価希望理由", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String spPriceApplyReason;

	/**
	 * 特価希望理由テキスト
	 */
	@Size(max = 255)
	@Schema(description = "特価希望理由テキスト", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String spPriceApplyReasonText;

	/**
	 * 主競合先名称
	 */
	@Size(max = 255)
	@Schema(description = "主競合先名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String mainCompetitorName;

	/**
	 * 競合情報
	 */
	@Size(max = 255)
	@Schema(description = "競合情報", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String competitionInfo;

	/**
	 * 競合先契約種別
	 */
	@Size(max = 255)
	@Schema(description = "競合先契約種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String competitionContractDiv;

	/**
	 * 競合先基本料金
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "競合先基本料金", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9223372036854775807.99]")
	private BigDecimal competitionAmount;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String extendsParameter;

	/**
	 * 帳票用消費税率区分
	 */
	@Size(max = 255)
	@Schema(description = "帳票用消費税率区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String issueTaxCodeValue;

	/**
	 * 見積ワークID
	 */
	@Size(max = 255)
	@Schema(description = "見積ワークID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String estimationWorkId;

	/**
	 * V-UP連携フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "V-UP連携フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer vupLinkageFlg;

	/**
	 * 見積承認ルート
	 */
	@Valid
	@OneToOne(mappedBy = "estimation")
	@Schema(description = "見積承認ルート", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private EstimationApprovalRouteDto estimationApprovalRoute;

	/**
	 * 見積添付ファイル
	 */
	@Valid
	@OneToMany(mappedBy = "estimation")
	@Schema(description = "見積添付ファイル", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<EstimationAttachedFileDto> estimationAttachedFileList;

	/**
	 * 見積担当SA社員
	 */
	@Valid
	@NotNull
	@OneToOne(mappedBy = "estimation")
	@Schema(description = "見積担当SA社員", requiredMode = Schema.RequiredMode.REQUIRED)
	private EstimationPicSaEmpDto estimationPicSaEmp;

	/**
	 * 見積追加編集者社員
	 */
	@Valid
	@OneToMany(mappedBy = "estimation")
	@Schema(description = "見積追加編集者社員", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<EstimationAddedEditorEmpDto> estimationAddedEditorEmpList;

	/**
	 * 販売店（見積用）
	 */
	@Valid
	@OneToMany(mappedBy = "estimation")
	@Schema(description = "販売店(見積用)", required = false)
	private List<DealerEstimationDto> dealerEstimationList;

	/**
	 * 顧客（見積用）
	 */
	@Valid
	@NotNull
	@OneToOne(mappedBy = "estimation")
	@Schema(description = "顧客(見積用)", required = true)
	private CustomerEstimationDto customerEstimation;

	/**
	 * 見積チェック結果
	 */
	@Valid
	@OneToMany(mappedBy = "estimation")
	@Schema(description = "見積チェック結果(作成時不要)", required = false)
	private List<EstimationCheckResultDto> estimationCheckResultList;

	/**
	 * 見積明細
	 */
	@Valid
	@OneToMany(mappedBy = "estimation")
	@Schema(description = "見積明細", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<EstimationDetailDto> estimationDetailList;

	/**
	 * 商品（見積用）
	 */
	@Valid
	@OneToMany(mappedBy = "estimation")
	@Schema(description = "商品（見積用）", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ProductEstimationDto> productEstimationList;

	/**
	 * アプリケーションID
	 */
	@Valid
	@Size(max = 255)
	@Schema(description = "アプリケーションID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String appId;

	/**
	 * RJ管理番号
	 */
	@Valid
	@Size(max = 255)
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 品種追加状態
	 */
	@Schema(description = "品種追加状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未実施(\"0\"), 実施中(\"1\"), 実施済み(\"2\")")
	private ItemAddStatus itemAddStatus;

	/**
	 * V-UP見積番号
	 */
	@Size(max = 255)
	@Schema(description = "V-UP見積番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vupEstimationNumber;

	/**
	 * 違約金明細(見積用)
	 */
	@Valid
	@Schema(description = "違約金明細(見積用)", required = false)
	private List<PenaltyDetailEstimationDto> penaltyDetailEstimationList;

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
	 * 電子契約連携状態
	 */
	@Schema(description = "電子契約連携状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "しない(\"0\"), 未連携(\"1\"), 連携済み(\"2\")", example = "1")
	private ElectronicContractLinkageStatus electronicContractLinkageStatus;

	/**
	 * 電子契約情報
	 */
	@Valid
	@OneToMany(mappedBy = "estimation")
	@Schema(description = "電子契約情報", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ElectronicContractInfoDto electronicContractInfo;
}
