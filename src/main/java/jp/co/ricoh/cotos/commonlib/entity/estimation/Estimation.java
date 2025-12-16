package jp.co.ricoh.cotos.commonlib.entity.estimation;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ContractChangeTiming;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ItemAddStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 見積を表すEntityです。
 */
@Entity
@EqualsAndHashCode(callSuper = true, exclude={"estimationApprovalRoute","estimationPicSaEmp","customerEstimation", "estimationCheckResultList", "operationLogList", "estimationAttachedFileList", "estimationAddedEditorEmpList", "dealerEstimationList", "estimationDetailList", "productEstimationList", "penaltyDetailEstimationList", "electronicContractInfo"})
@ToString(callSuper = true, exclude={"estimationApprovalRoute","estimationPicSaEmp","customerEstimation", "estimationCheckResultList", "operationLogList", "estimationAttachedFileList", "estimationAddedEditorEmpList", "dealerEstimationList", "estimationDetailList", "productEstimationList", "penaltyDetailEstimationList", "electronicContractInfo"})
@EntityListeners(EstimationListener.class)
@Data
@Table(name = "estimation")
public class Estimation extends EntityBase {

	@Description(value = "ライフサイクル状態")
	public enum LifecycleStatus {

		作成中("1"), 作成完了("2"), 受注("3"), 失注("4"), 破棄("5");

		private final String text;

		private LifecycleStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static LifecycleStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "ワークフロー状態")
	public enum WorkflowStatus {

		作成中("1"), 業務依頼中("2"), 業務処理完了("3"), 承認依頼中("4"), 承認済("5"), 顧客提示済("6");

		private final String text;

		private WorkflowStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static WorkflowStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "見積種別")
	public enum EstimationType {

		新規("1"), 契約変更("2");

		private final String text;

		private EstimationType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static EstimationType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "電子契約連携状態")
	public enum ElectronicContractLinkageStatus {

		しない("0"), 未連携("1"), 連携済("2");

		private final String text;

		private ElectronicContractLinkageStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ElectronicContractLinkageStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estimation_seq")
	@SequenceGenerator(name = "estimation_seq", sequenceName = "estimation_seq", allocationSize = 1)
	@Schema(description = "見積ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 商品グループマスタID
	 */
	@Min(0)
	@Column(nullable = false)
	@Schema(description = "商品グループマスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long productGrpMasterId;

	/**
	 * ライフサイクル状態
	 */
	@Column(nullable = false)
	@Schema(description = "ライフサイクル状態(作成時不要)", required = true, allowableValues = "作成中(\"1\"), 作成完了(\"2\"), 受注(\"3\"), 失注(\"4\"), 破棄(\"5\")", example = "1", readOnly = true)
	private LifecycleStatus lifecycleStatus;

	/**
	 * ワークフロー状態
	 */
	@Column(nullable = false)
	@Schema(description = "ワークフロー状態(作成時不要)", required = true, allowableValues = "作成中(\"1\"), 業務依頼中(\"2\"), 業務処理完了(\"3\"), 承認依頼中(\"4\"), 承認済(\"5\"), 顧客提示済(\"6\")", example = "1", readOnly = true)
	private WorkflowStatus workflowStatus;

	/**
	 * 恒久契約識別番号
	 */
	@Schema(description = "恒久契約識別番号(作成時不要)", required = false, allowableValues = "range[0,255]", readOnly = true)
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
	@Column(nullable = false)
	@Schema(description = "見積番号(作成時不要)", required = true, allowableValues = "range[0,255]", readOnly = true)
	private String estimationNumber;

	/**
	 * 見積番号枝番
	 */
	@Column(nullable = false)
	@Schema(description = "見積番号枝番(作成時不要)", required = true, allowableValues = "range[0,99]", readOnly = true)
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
	@Column(nullable = false)
	@Schema(description = "見積種別(作成時不要)", required = true, allowableValues = "新規(\"1\"), 契約変更(\"2\")", example = "1", readOnly = true)
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

	/** 見積鑑用納期 */
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
	@Digits(integer = 19, fraction = 2)
	@DecimalMin("0.00")
	@Schema(description = "競合先基本料金", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
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
	@Schema(description = "見積ワークID(作成時不要)", required = false, allowableValues = "range[0,255]", readOnly = true)
	private String estimationWorkId;

	/**
	 * V-UP連携フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "V-UP連携フラグ(作成時不要)", required = false, allowableValues = "range[0,9]", readOnly = true)
	private Integer vupLinkageFlg;

	/**
	 * 見積承認ルート
	 */
	@OneToOne(mappedBy = "estimation")
	@Schema(description = "見積承認ルート(作成時不要)", required = false, readOnly = true)
	private EstimationApprovalRoute estimationApprovalRoute;

	/**
	 * 見積操作履歴
	 */
	@OneToMany(mappedBy = "estimation")
	@OrderBy("operatedAt ASC")
	@Schema(description = "見積操作履歴(作成時不要)", required = false, readOnly = true)
	private List<OperationLog> operationLogList;

	/**
	 * 見積添付ファイル
	 */
	@Valid
	@OneToMany(mappedBy = "estimation")
	@Schema(description = "見積添付ファイル", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<EstimationAttachedFile> estimationAttachedFileList;

	/**
	 * 見積担当SA社員
	 */
	@Valid
	@OneToOne(mappedBy = "estimation")
	@Schema(description = "見積担当SA社員", requiredMode = Schema.RequiredMode.REQUIRED)
	private EstimationPicSaEmp estimationPicSaEmp;

	/**
	 * 見積追加編集者社員
	 */
	@Valid
	@OneToMany(mappedBy = "estimation")
	@Schema(description = "見積追加編集者社員", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<EstimationAddedEditorEmp> estimationAddedEditorEmpList;

	/**
	 * 販売店（見積用）
	 */
	@Valid
	@OneToMany(mappedBy = "estimation")
	@Schema(description = "販売店(見積用)", required = false)
	private List<DealerEstimation> dealerEstimationList;

	/**
	 * 顧客（見積用）
	 */
	@Valid
	@OneToOne(mappedBy = "estimation")
	@Schema(description = "顧客(見積用)", required = true)
	private CustomerEstimation customerEstimation;

	/**
	 * 見積チェック結果
	 */
	@OneToMany(mappedBy = "estimation")
	@OrderBy("displayOrder ASC")
	@Schema(description = "見積チェック結果(作成時不要)", required = false, readOnly = true)
	private List<EstimationCheckResult> estimationCheckResultList;

	/**
	 * 見積明細
	 */
	@Valid
	@OneToMany(mappedBy = "estimation")
	@Schema(description = "見積明細", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<EstimationDetail> estimationDetailList;

	/**
	 * 商品（見積用）
	 */
	@OneToMany(mappedBy = "estimation")
	@Schema(description = "商品（見積用）(作成時不要)", required = false, readOnly = true)
	private List<ProductEstimation> productEstimationList;

	/**
	 * アプリケーションID
	 */
	@Size(max = 255)
	@Schema(description = "アプリケーションID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String appId;

	/**
	 * RJ管理番号
	 */
	@Column
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 品種追加状態
	 */
	@Schema(description = "品種追加状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未実施(\"0\"), 実施中(\"1\"), 実施済み(\"2\")")
	private ItemAddStatus itemAddStatus;

	/**
	 * web受注注文番号
	 */
	@Size(max = 255)
	@Schema(description = "web受注注文番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String webOrderNumber;

	/**
	 * ベンダー管理番号
	 */
	@Size(max = 255)
	@Schema(description = "ベンダー管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vendorManageNumber;

	/**
	 * 申込日
	 */
	@Schema(description = "申込日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date applicationDate;

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
	@OneToMany(mappedBy = "estimation")
	@Schema(description = "違約金明細(見積用)", required = false, readOnly = true)
	private List<PenaltyDetailEstimation> penaltyDetailEstimationList;

	/**
	 * サービス利用希望日
	 */
	@Schema(description = "サービス利用希望日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date conclusionPreferredDate;

	/**
	 * 契約変更タイミング
	 */
	@Schema(description = "契約変更タイミング", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "自動更新時(\"0\"),契約期間途中(\"1\")")
	private ContractChangeTiming contractChangeTiming;

	/**
	 * 延長可能契約月数
	 */
	@Schema(description = "延長可能契約月数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Long maxExtensionMonths;

	/**
	 * 電子契約連携状態
	 */
	@Schema(description = "電子契約連携状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "しない(\"0\"), 未連携(\"1\"), 連携済み(\"2\")", example = "1")
	private ElectronicContractLinkageStatus electronicContractLinkageStatus;

	/**
	 * 電子契約情報
	 */
	@Valid
	@OneToOne(mappedBy = "estimation")
	@Schema(description = "電子契約情報", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private ElectronicContractInfo electronicContractInfo;
}
