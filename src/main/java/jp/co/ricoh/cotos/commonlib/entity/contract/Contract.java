package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ContractChangeTiming;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.EimLinkedStatus;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ItemAddStatus;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.MvbAccountEntryDiv;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.RpaLinkageCpqUpdateCsvCreateStatus;
import jp.co.ricoh.cotos.commonlib.entity.accounting.AccountingPeriodDetail;
import jp.co.ricoh.cotos.commonlib.serializer.UnixTimestampDateSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 契約鑑を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true, exclude = {"contractPicSaEmp", "customerContract", "contractPicMntCeEmp", "contractPicMntSsOrg", "contractPicAccSsOrg", "contractPicIntSsOrg", "contractPicAccCeEmp", "contractPicIntCeEmp", "contractInstallationLocation", "shippingAddress", "shippingAddressSsOrg", "collectLocation", "contractDetailList", "contractCheckResultList", "contractApprovalRouteList", "contractAttachedFileList", "contractAddedEditorEmpList", "dealerContractList", "contractOperationLogList", "productContractList", "contractAttachedFileHistoryList", "contractEquipmentList", "managedEstimationDetailList", "contractEquipmentNoIsysoneList", "managedContractEquipmentStatusList", "penaltyDetailContractList", "accountingPeriodDetailList", "contractEquipmentAdditionInfoList"})
@ToString(callSuper = true, exclude = {"contractPicSaEmp", "customerContract", "contractPicMntCeEmp", "contractPicMntSsOrg", "contractPicAccSsOrg", "contractPicIntSsOrg", "contractPicAccCeEmp", "contractPicIntCeEmp", "contractInstallationLocation", "shippingAddress", "shippingAddressSsOrg", "collectLocation", "contractDetailList", "contractCheckResultList", "contractApprovalRouteList", "contractAttachedFileList", "contractAddedEditorEmpList", "dealerContractList", "contractOperationLogList", "productContractList", "contractAttachedFileHistoryList", "contractEquipmentList", "managedEstimationDetailList", "contractEquipmentNoIsysoneList", "managedContractEquipmentStatusList", "penaltyDetailContractList", "accountingPeriodDetailList", "contractEquipmentAdditionInfoList"})
@Data
@Table(name = "contract")
@EntityListeners(ContractListener.class)
public class Contract extends EntityBase {

	@Description(value = "契約種別")
	public enum ContractType {

		新規("1"), 契約変更("2"), 情報変更("3"), 契約更新("4");

		private final String text;

		private ContractType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ContractType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "ライフサイクル状態（契約）")
	public enum LifecycleStatus {

		作成中("1"), 作成完了("2"), キャンセル手続き中("3"), 破棄("4"), 予定日待ち("5"), 締結中("6"), 解約手続き中("7"), 解約予定日待ち("8"), 解約("9"), 旧契約("10"), 締結待ち("11");

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

	@Description(value = "ワークフロー状態（契約）")
	public enum WorkflowStatus {

		作成中("1"), 承認依頼中("2"), 承認済("3"), 業務依頼中("4"), 業務処理完了("5"), キャンセル申請中("6"), 売上可能("7"), 解約申請中("8");

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

	@Description(value = "IFS連携用CSV作成状態、IFS連携用解約CSV作成状態")
	public enum IfsLinkageCsvCreateStatus {

		未作成("0"), 作成済み("1"), 作成対象外("2"), 作成エラー("3");

		private final String text;

		private IfsLinkageCsvCreateStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static IfsLinkageCsvCreateStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "S&S作業依頼作成状態")
	public enum SsWorkRequestCreateStatus {
		未作成("0"), 作成済み("1"), 作成エラー("2");

		private final String text;

		private SsWorkRequestCreateStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static SsWorkRequestCreateStatus fromString(final String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "販売区分")
	public enum SaleDiv {

		訪問販売("1"), Web販売("2");

		private final String text;

		private SaleDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static SaleDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "統合契約連携用CSV作成状態、統合契約連携用CSV作成状態（解約）")
	public enum AbsConCsvCreateStatus {

		未作成("0"), 作成済み("1"), 作成エラー("2");

		private final String text;

		private AbsConCsvCreateStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static AbsConCsvCreateStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "ARCS期間売保守元契約処理状態")
	public enum ArcsPeriodSaleMntOriginStatus {

		未作成("0"), CSV作成済み("1"), 対象外("2"), 管理対象外("3");

		private final String text;

		private ArcsPeriodSaleMntOriginStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ArcsPeriodSaleMntOriginStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "基本契約区分")
	public enum BasicContractDiv {

		基本契約("1"), 基本契約_一部("2");

		private final String text;

		private BasicContractDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static BasicContractDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "契約状態遷移制御区分")
	public enum ContractStatusControlType {

		手配の作業完了をもって契約状態を進める("0"), 手配の作業完了を待たずに契約状態を進める("1");

		private final String text;

		private ContractStatusControlType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ContractStatusControlType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_seq")
	@SequenceGenerator(name = "contract_seq", sequenceName = "contract_seq", allocationSize = 1)
	@Schema(description = "契約ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約種別
	 */
	@Column(nullable = false)
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
	@Column(nullable = false)
	@Schema(description = "売上計上フラグ(作成時不要)", required = true, allowableValues = "range[0,9]", readOnly = true)
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
	@Schema(description = "課金開始日(ランニング)", required = false)
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
	@Schema(description = "S&S作業依頼作成状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未作成(\"0\"),作成済み(\"1\"),作成エラー(\"2\")")
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
	@OneToMany(mappedBy = "contract")
	@Schema(description = "契約明細", requiredMode = Schema.RequiredMode.REQUIRED)
	private List<ContractDetail> contractDetailList;

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
	@Schema(description = "契約担当SA社員", requiredMode = Schema.RequiredMode.REQUIRED)
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
	private List<ProductContract> productContractList;

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
	@Schema(description = "見積明細管理", requiredMode = Schema.RequiredMode.REQUIRED)
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
	@Schema(description = "アプリケーションID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String appId;

	/**
	 * 契約自動更新日
	 */
	@Column
	@Temporal(TemporalType.DATE)
	@Schema(description = "契約自動更新日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date contractAutoUpdateDate;

	/**
	 * 届先コード
	 */
	@Column
	@Schema(description = "届先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String deliveryCd;

	/**
	 * 届先名
	 */
	@Column
	@Schema(description = "届先名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String deliveryName;

	/**
	 * 検収日
	 */
	@Column
	@Temporal(TemporalType.DATE)
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
	@Temporal(TemporalType.DATE)
	@Schema(description = "解約確定日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date cancelDecisionDate;

	/**
	 * 契約情報確定日
	 */
	@Column
	@Temporal(TemporalType.DATE)
	@Schema(description = "契約情報確定日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date fixedDate;

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
	 * 解約申込日
	 */
	@Schema(description = "解約申込日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date cancelApplicationDate;

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
	@Schema(description = "統合契約連携用CSV作成状態(解約)", required = false, allowableValues = "未作成(\"0\"), 作成済み(\"1\"),作成エラー(\"2\")")
	private AbsConCsvCreateStatus absConCsvCreateStatusCancel;

	/**
	 * 統合契約連携用CSV作成日(解約)
	 */
	@Schema(description = "統合契約連携用CSV作成日(解約)", required = false)
	@Temporal(TemporalType.DATE)
	private Date absConCsvCreateDateCancel;

	/**
	 * EIM連携済状態
	 */
	@Schema(description = "EIM連携済状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未連携(\"0\"), 連携済(\"1\"),対象外 (\"9\")")
	private EimLinkedStatus eimLinkedStatus;

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
	 * 違約金用FFM発注問合せ番号
	 */
	@Size(max = 255)
	@Schema(description = "違約金用FFM発注問合せ番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String penaltyFfmOrderContactNo;

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
	 * 契約機種(Isys-Oneへの連携なし)
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "契約機種(Isys-Oneへの連携なし)", required = false)
	private List<ContractEquipmentNoIsysone> contractEquipmentNoIsysoneList;

	/**
	 * 契約機種状態管理
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "契約機種状態管理", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<ManagedContractEquipmentStatus> managedContractEquipmentStatusList;

	/**
	 * 配送先
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "配送先", requiredMode = Schema.RequiredMode.REQUIRED)
	private ShippingAddress shippingAddress;

	/**
	 * 違約金明細(契約用)
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "違約金明細(契約用)", required = false)
	private List<PenaltyDetailContract> penaltyDetailContractList;

	/**
	 * 配送先SS組織
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@Schema(description = "配送先SS組織", requiredMode = Schema.RequiredMode.REQUIRED)
	private ShippingAddressSsOrg shippingAddressSsOrg;

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
	 * ARCS期間売保守元契約処理状態
	 */
	@Schema(description = "ARCS期間売保守元契約処理状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未作成(\"0\"),CSV作成済み(\"1\"),対象外(\"2\"),管理対象外(\"3\")")
	private ArcsPeriodSaleMntOriginStatus arcsPeriodSaleMntOriginStatus;

	/**
	 * ARCS期間売保守元契約連携日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = UnixTimestampDateSerializer.class)
	@Schema(description = "ARCS期間売保守元契約連携日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date arcsPeriodSaleMntOriginLinkAt;

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
	 * 契約状態遷移制御区分
	 */
	@Schema(description = "契約状態遷移制御区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "手配の作業完了をもって契約状態を進める(\"0\"),手配の作業完了を待たずに契約状態を進める(\"1\")")
	private ContractStatusControlType contractStatusControlType;

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
	 * 更新用課金開始日
	 */
	@Schema(description = "更新用課金開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date billingStartDateForUpdate;

	/**
	 * 計上期間明細
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@OrderBy("item_master_id ASC, accounting_period_start ASC")
	@Schema(description = "計上期間明細", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<AccountingPeriodDetail> accountingPeriodDetailList;

	/**
	 * 契約変更タイミング
	 */
	@Schema(description = "契約変更タイミング", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "自動更新時(\"0\"),契約期間途中(\"1\")")
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
	private CollectLocation collectLocation;

	/**
	 * RPA連携用CPQ更新CSV作成状態
	 */
	@Schema(description = "RPA連携用CPQ更新CSV作成状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "連携済み(\"1\"),対象外(\"2\")")
	private RpaLinkageCpqUpdateCsvCreateStatus rpaLinkageCpqUpdateCsvCreateStatus;

	/**
	 * RPA連携用CPQ更新CSV作成日
	 */
	@Schema(description = "RPA連携用CPQ更新CSV作成日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = UnixTimestampDateSerializer.class)
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
	private List<ContractEquipmentAdditionInfo> contractEquipmentAdditionInfoList;

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

	/**
	 * 次回更新明細情報
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@Schema(description = "次回更新明細情報", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private List<NextUpdateDetailInfo> nextUpdateDetailInfoList;

}