package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.EimLinkedStatus;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.ItemAddStatus;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.MvbAccountEntryDiv;
import jp.co.ricoh.cotos.commonlib.entity.accounting.AccountingPeriodDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 契約鑑を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
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

		未作成("0"), CSV作成済み("1"), 対象外("2");

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
	@ApiModelProperty(value = "契約ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約種別
	 */
	@Column(nullable = false)
	@NotNull
	@ApiModelProperty(value = "契約種別", required = true, allowableValues = "新規(\"1\"), 契約変更(\"2\"), 情報変更(\"3\"), 契約更新(\"4\")", position = 2)
	private ContractType contractType;

	/**
	 * 商品グループマスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "商品グループマスタID", required = false, position = 3, allowableValues = "range[0,9223372036854775807]")
	private long productGrpMasterId;

	/**
	 * ライフサイクル状態
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "ライフサイクル状態(作成時不要)", required = true, allowableValues = "作成中(\"1\"), 作成完了(\"2\"), キャンセル手続き中(\"3\"), 破棄(\"4\"), 予定日待ち(\"5\"), 締結中(\"6\"), 解約手続き中(\"7\"), 解約予定日待ち(\"8\"), 解約(\"9\"), 旧契約(\"10\")", example = "1", position = 4, readOnly = true)
	private LifecycleStatus lifecycleStatus;

	/**
	 * ワークフロー状態
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "ワークフロー状態(作成時不要)", required = true, allowableValues = "作成中(\"1\"), 承認依頼中(\"2\"), 承認済(\"3\"), 業務依頼中(\"4\"), 業務処理完了(\"5\"), キャンセル申請中(\"6\"), 売上可能(\"7\"), 解約申請中(\"8\")", example = "1", position = 5, readOnly = true)
	private WorkflowStatus workflowStatus;

	/**
	 * 恒久契約識別番号
	 */
	@ApiModelProperty(value = "恒久契約識別番号(作成時不要)", required = false, position = 6, allowableValues = "range[0,255]", readOnly = true)
	private String immutableContIdentNumber;

	/**
	 * 案件番号
	 */
	@ApiModelProperty(value = "案件番号", required = false, position = 7, allowableValues = "range[0,255]")
	@Size(max = 255)
	private String caseNumber;

	/**
	 * 案件名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "案件名", required = false, position = 8, allowableValues = "range[0,255]")
	private String caseTitle;

	/**
	 * 契約番号
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "契約番号(作成時不要)", required = true, position = 9, allowableValues = "range[0,255]", readOnly = true)
	private String contractNumber;

	/**
	 * 契約番号枝番
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "契約番号枝番(作成時不要)", required = true, position = 10, allowableValues = "range[0,99]", readOnly = true)
	private int contractBranchNumber;

	/**
	 * 契約件名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "契約件名", required = false, position = 11, allowableValues = "range[0,255]")
	private String contractTitle;

	/**
	 * 変更元契約番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "変更元契約番号", required = false, position = 12, allowableValues = "range[0,255]")
	private String originContractNumber;

	/**
	 * 変更元契約番号枝番
	 */
	@Max(99)
	@Min(0)
	@ApiModelProperty(value = "変更元契約番号枝番", required = false, position = 13, allowableValues = "range[0,99]")
	private Integer originContractBranchNumber;

	/**
	 * 変更元契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "変更元契約ID", required = false, position = 14, allowableValues = "range[0,9223372036854775807]")
	private Long originContractId;

	/**
	 * 変更希望日
	 */
	@ApiModelProperty(value = "変更希望日", required = false, position = 15)
	@Temporal(TemporalType.DATE)
	private Date changePreferredDate;

	/**
	 * 契約日
	 */
	@ApiModelProperty(value = "契約日", required = false, position = 16)
	@Temporal(TemporalType.DATE)
	private Date contractDate;

	/**
	 * 売上計上フラグ
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "売上計上フラグ(作成時不要)", required = true, position = 17, allowableValues = "range[0,9]", readOnly = true)
	private int accountSalesFlg;

	/**
	 * 請求開始日
	 */
	@ApiModelProperty(value = "請求開始日", required = false, position = 18)
	@Temporal(TemporalType.DATE)
	private Date billingDate;

	/**
	 * サービス開始日
	 */
	@ApiModelProperty(value = "サービス開始日", required = false, position = 19)
	@Temporal(TemporalType.DATE)
	private Date serviceTermStart;

	/**
	 * サービス終了日
	 */
	@ApiModelProperty(value = "サービス終了日", required = false, position = 20)
	@Temporal(TemporalType.DATE)
	private Date serviceTermEnd;

	/**
	 * 解約予定日
	 */
	@ApiModelProperty(value = "解約予定日", required = false, position = 21)
	@Temporal(TemporalType.DATE)
	private Date cancelScheduledDate;

	/**
	 * 見積番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "見積番号", required = false, position = 22, allowableValues = "range[0,255]")
	private String estimationNumber;

	/**
	 * 見積番号枝番
	 */
	@Max(99)
	@Min(0)
	@ApiModelProperty(value = "見積番号枝番", required = false, position = 23, allowableValues = "range[0,99]")
	private Integer estimationBranchNumber;

	/**
	 * 見積ID
	 */
	@Min(0)
	@ApiModelProperty(value = "見積ID", required = false, position = 24, allowableValues = "range[0,9223372036854775807]")
	private Long estimationId;

	/**
	 * 見積件名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "見積件名", required = false, position = 25, allowableValues = "range[0,255]")
	private String estimationTitle;

	/**
	 * 商流区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "商流区分", required = false, position = 26, allowableValues = "range[0,255]")
	private String commercialFlowDiv;

	/**
	 * 発行書式
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "発行書式", required = false, position = 27, allowableValues = "range[0,255]")
	private String issueFormat;

	/**
	 * 得意先コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "得意先コード<br/>※POST時「MoM請求売上先サイト情報マスタ」存在チェック実施", required = false, position = 28, allowableValues = "range[0,255]")
	private String billingCustomerSpCode;

	/**
	 * 得意先宛先名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "得意先宛先名", required = false, position = 29, allowableValues = "range[0,255]")
	private String billingCustomerSpName;

	/**
	 * 支払条件
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "支払条件", required = false, position = 30, allowableValues = "range[0,255]")
	private String paymentTerms;

	/**
	 * 支払方法
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "支払方法", required = false, position = 31, allowableValues = "range[0,255]")
	private String paymentMethod;

	/**
	 * 解約理由
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "解約理由", required = false, position = 32, allowableValues = "range[0,255]")
	private String cancelReason;

	/**
	 * その他解約理由
	 */
	@Size(max = 1000)
	@ApiModelProperty(value = "その他解約理由", required = false, position = 33, allowableValues = "range[0,1000]")
	private String cancelReasonEtc;

	/**
	 * 拡張項目
	 */
	@ApiModelProperty(value = "拡張項目", required = false, position = 34)
	@Lob
	private String extendsParameter;

	/**
	 * web受注注文番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "web受注注文番号", required = false, position = 35, allowableValues = "range[0,255]")
	private String webOrderNumber;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 36, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 課金開始日(ランニング)
	 */
	@ApiModelProperty(value = "課金開始日(ランニング)", required = false, position = 37)
	@Temporal(TemporalType.DATE)
	private Date billingStartDate;

	/**
	 * 解約注文番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "解約注文番号", required = false, position = 38, allowableValues = "range[0,255]")
	private String cancelOrderNo;

	/**
	 * サービス利用希望日
	 */
	@ApiModelProperty(value = "サービス利用希望日", required = false, position = 39)
	@Temporal(TemporalType.DATE)
	private Date conclusionPreferredDate;

	/**
	 * IFS連携用CSV作成状態
	 */
	@ApiModelProperty(value = "IFS連携用CSV作成状態", required = false, position = 40, allowableValues = "未作成(\"0\"), 作成済み(\"1\"), 作成対象外(\"2\"), 作成エラー(\"3\")")
	private IfsLinkageCsvCreateStatus ifsLinkageCsvCreateStatus;

	/**
	 * IFS連携用CSV作成日
	 */
	@ApiModelProperty(value = "IFS連携用CSV作成日", required = false, position = 41)
	@Temporal(TemporalType.DATE)
	private Date ifsLinkageCsvCreateDate;

	/**
	 * お問い合わせ番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "お問い合わせ番号", required = false, position = 42, allowableValues = "range[0,255]")
	private String contactNo;

	/**
	 * S&S作業依頼作成状態
	 */
	@ApiModelProperty(value = "S&S作業依頼作成状態", required = false, position = 43, allowableValues = "未作成(\"0\"),作成済み(\"1\"),作成エラー(\"2\")")
	private SsWorkRequestCreateStatus ssWorkRequestCreateStatus;

	/**
	 * 帳票用消費税率区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "帳票用消費税率区分", required = false, position = 44, allowableValues = "range[0,255]")
	private String issueTaxCodeValue;

	/**
	 * 申込日
	 */
	@ApiModelProperty(value = "申込日", required = false, position = 45)
	@Temporal(TemporalType.DATE)
	private Date applicationDate;

	/**
	 * 契約明細
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@ApiModelProperty(value = "契約明細", required = true, position = 46)
	private List<ContractDetail> contractDetailList;

	/**
	 * 契約チェック結果
	 */
	@OneToMany(mappedBy = "contract")
	@OrderBy("displayOrder ASC")
	@ApiModelProperty(value = "契約チェック結果(作成時不要)", required = false, position = 47, readOnly = true)
	private List<ContractCheckResult> contractCheckResultList;

	/**
	 * 契約承認ルート
	 */
	@OneToMany(mappedBy = "contract")
	@ApiModelProperty(value = "契約承認ルート(作成時不要)", required = false, position = 48, readOnly = true)
	private List<ContractApprovalRoute> contractApprovalRouteList;

	/**
	 * 契約添付ファイル
	 */
	@OneToMany(mappedBy = "contract")
	@ApiModelProperty(value = "契約添付ファイル(作成時不要)", required = false, position = 49, readOnly = true)
	private List<ContractAttachedFile> contractAttachedFileList;

	/**
	 * 契約担当SA社員
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@ApiModelProperty(value = "契約担当SA社員", required = true, position = 50)
	private ContractPicSaEmp contractPicSaEmp;

	/**
	 * 契約追加編集者社員
	 */
	@OneToMany(mappedBy = "contract")
	@ApiModelProperty(value = "契約追加編集者社員(作成時不要)", required = false, position = 51, readOnly = true)
	private List<ContractAddedEditorEmp> contractAddedEditorEmpList;

	/**
	 * 販売店(契約用)
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@ApiModelProperty(value = "販売店(契約用)", required = false, position = 52)
	private List<DealerContract> dealerContractList;

	/**
	 * 顧客(契約用)
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@ApiModelProperty(value = "顧客(契約用)", required = true, position = 53)
	private CustomerContract customerContract;

	/**
	 * 契約操作履歴
	 */
	@OneToMany(mappedBy = "contract")
	@OrderBy("operatedAt ASC")
	@ApiModelProperty(value = "契約操作履歴(作成時不要)", required = true, position = 54, readOnly = true)
	private List<ContractOperationLog> contractOperationLogList;

	/**
	 * 商品(契約用)
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@ApiModelProperty(value = "商品(契約用)", required = true, position = 55)
	private List<ProductContract> productContractList;

	/**
	 * 契約保守担当CE社員
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@ApiModelProperty(value = "契約保守担当CE社員(作成時不要)", required = false, position = 56, readOnly = true)
	private ContractPicMntCeEmp contractPicMntCeEmp;

	/**
	 * 契約保守担当SS組織
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@ApiModelProperty(value = "契約保守担当SS組織(作成時不要)", required = false, position = 57, readOnly = true)
	private ContractPicMntSsOrg contractPicMntSsOrg;

	/**
	 * 契約受付担当SS組織
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@ApiModelProperty(value = "契約受付担当SS組織(作成時不要)", required = false, position = 58, readOnly = true)
	private ContractPicAccSsOrg contractPicAccSsOrg;

	/**
	 * 契約導入担当SS組織
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@ApiModelProperty(value = "契約導入担当SS組織(作成時不要)", required = false, position = 59, readOnly = true)
	private ContractPicIntSsOrg contractPicIntSsOrg;

	/**
	 * 契約添付ファイル履歴
	 */
	@OneToMany(mappedBy = "contract")
	@ApiModelProperty(value = "契約添付ファイル履歴(作成時不要)", required = true, position = 60)
	private List<ContractAttachedFileHistory> contractAttachedFileHistoryList;

	/**
	 * 契約受付担当CE社員
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@ApiModelProperty(value = "契約受付担当CE社員(作成時不要)", required = false, position = 61, readOnly = true)
	private ContractPicAccCeEmp contractPicAccCeEmp;

	/**
	 * 契約導入担当CE社員
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@ApiModelProperty(value = "契約導入担当CE社員(作成時不要)", required = false, position = 62, readOnly = true)
	private ContractPicIntCeEmp contractPicIntCeEmp;

	/**
	 * 契約機種
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@ApiModelProperty(value = "契約機種(作成時不要)", required = false, position = 63, readOnly = true)
	private List<ContractEquipment> contractEquipmentList;

	/**
	 * 見積明細管理
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@ApiModelProperty(value = "見積明細管理", required = true, position = 64)
	private List<ManagedEstimationDetail> managedEstimationDetailList;

	/**
	 * 設置先(契約用)
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@ApiModelProperty(value = "設置先(契約用)", required = true, position = 65)
	private ContractInstallationLocation contractInstallationLocation;

	/**
	 * アプリケーションID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "アプリケーションID", required = false, position = 66, allowableValues = "range[0,255]")
	private String appId;

	/**
	 * 契約自動更新日
	 */
	@Column
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "契約自動更新日", required = false, position = 67)
	private Date contractAutoUpdateDate;

	/**
	 * 届先コード
	 */
	@Column
	@ApiModelProperty(value = "届先コード", required = false, position = 68, allowableValues = "range[0,]")
	private String deliveryCd;

	/**
	 * 届先名
	 */
	@Column
	@ApiModelProperty(value = "届先名", required = false, position = 69, allowableValues = "range[0,]")
	private String deliveryName;

	/**
	 * 検収日
	 */
	@Column
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "検収日", required = false, position = 70)
	private Date acceptanceDate;

	/**
	 * 設置届先サイトID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "設置届先サイトID", required = false, position = 71, allowableValues = "range[0,255]")
	private String installDeliverySiteId;

	/**
	 * 解約確定日
	 */
	@Column
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "解約確定日", required = false, position = 72)
	private Date cancelDecisionDate;

	/**
	 * 契約情報確定日
	 */
	@Column
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "契約情報確定日", required = false, position = 73)
	private Date fixedDate;

	/**
	 * 販売区分
	 */
	@ApiModelProperty(value = "販売区分", required = false, position = 74, allowableValues = "訪問販売(\"1\"), Web販売(\"2\")")
	private SaleDiv saleDiv;

	/**
	 * ベンダー管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ベンダー管理番号", required = false, position = 75, allowableValues = "range[0,255]")
	private String vendorManageNumber;

	/**
	 * 手動更新フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "手動更新フラグ", required = false, position = 76, allowableValues = "range[0,9]")
	private Integer manualUpdateFlg;

	/**
	 * 品種追加状態
	 */
	@ApiModelProperty(value = "品種追加状態", required = false, position = 77, allowableValues = "未実施(\"0\"), 実施中(\"1\"), 実施済み(\"2\")")
	private ItemAddStatus itemAddStatus;

	/**
	 * 解約申込日
	 */
	@ApiModelProperty(value = "解約申込日", required = false, position = 78)
	@Temporal(TemporalType.DATE)
	private Date cancelApplicationDate;

	/**
	 * 統合契約連携用CSV作成状態
	 */
	@ApiModelProperty(value = "統合契約連携用CSV作成状態", required = false, position = 79, allowableValues = "未作成(\"0\"), 作成済み(\"1\"),作成エラー(\"2\")")
	private AbsConCsvCreateStatus absConCsvCreateStatus;

	/**
	 * 統合契約連携用CSV作成日
	 */
	@ApiModelProperty(value = "統合契約連携用CSV作成日", required = false, position = 80)
	@Temporal(TemporalType.DATE)
	private Date absConCsvCreateDate;

	/**
	 * 統合契約連携用CSV作成状態(解約)
	 */
	@ApiModelProperty(value = "統合契約連携用CSV作成状態(解約)", required = false, position = 81, allowableValues = "未作成(\"0\"), 作成済み(\"1\"),作成エラー(\"2\")")
	private AbsConCsvCreateStatus absConCsvCreateStatusCancel;

	/**
	 * 統合契約連携用CSV作成日(解約)
	 */
	@ApiModelProperty(value = "統合契約連携用CSV作成日(解約)", required = false, position = 82)
	@Temporal(TemporalType.DATE)
	private Date absConCsvCreateDateCancel;

	/**
	 * EIM連携済状態
	 */
	@ApiModelProperty(value = "EIM連携済状態", required = false, position = 83, allowableValues = "未連携(\"0\"), 連携済(\"1\"),対象外 (\"9\")")
	private EimLinkedStatus eimLinkedStatus;

	/**
	 * IFS連携用解約CSV作成状態
	 */
	@ApiModelProperty(value = "IFS連携用解約CSV作成状態", required = false, position = 84, allowableValues = "未作成(\"0\"), 作成済み(\"1\"), 作成対象外(\"2\"), 作成エラー(\"3\")")
	private IfsLinkageCsvCreateStatus ifsLinkageCancelCsvStatus;

	/**
	 * IFS連携用解約CSV作成日
	 */
	@ApiModelProperty(value = "IFS連携用解約CSV作成日", required = false, position = 85)
	@Temporal(TemporalType.DATE)
	private Date ifsLinkageCancelCsvDate;

	/**
	 * 違約金用FFM発注問合せ番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "違約金用FFM発注問合せ番号", required = false, position = 86, allowableValues = "range[0,255]")
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
	@ApiModelProperty(value = "納品書・請求書印字用コメント", required = false, position = 87, allowableValues = "range[0,255]")
	private String purchaseManageNumber;

	/**
	 * 契約機種(Isys-Oneへの連携なし)
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@ApiModelProperty(value = "契約機種(Isys-Oneへの連携なし)", required = false, position = 88)
	private List<ContractEquipmentNoIsysone> contractEquipmentNoIsysoneList;

	/**
	 * 契約機種状態管理
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@ApiModelProperty(value = "契約機種状態管理", required = false, position = 89)
	private List<ManagedContractEquipmentStatus> managedContractEquipmentStatusList;

	/**
	 * 配送先
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@ApiModelProperty(value = "配送先", required = true, position = 90)
	private ShippingAddress shippingAddress;

	/**
	 * 違約金明細(契約用)
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@ApiModelProperty(value = "違約金明細(契約用)", required = false, position = 91)
	private List<PenaltyDetailContract> penaltyDetailContractList;

	/**
	 * 配送先SS組織
	 */
	@Valid
	@OneToOne(mappedBy = "contract")
	@ApiModelProperty(value = "配送先SS組織", required = true, position = 92)
	private ShippingAddressSsOrg shippingAddressSsOrg;

	/**
	 * ベンダー向けコメント
	 */
	@Size(max = 1333)
	@ApiModelProperty(value = "ベンダー向けコメント", required = false, position = 93, allowableValues = "range[0,1333]")
	private String toVendorComment;

	/**
	 * MVBアカウント
	 */
	@Size(max = 18)
	@ApiModelProperty(value = "MVBアカウント", required = false, position = 94, allowableValues = "range[0,18]")
	private String mvbAccount;

	/**
	 * MVBアカウント登録区分
	 */
	@ApiModelProperty(value = "MVBアカウント登録区分", required = false, position = 95, allowableValues = "新規登録(\"1\"), 既存使用(\"2\")")
	private MvbAccountEntryDiv mvbAccountEntryDiv;

	/**
	 * S&S作業依頼フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "S&S作業依頼フラグ", required = false, position = 96, allowableValues = "range[0,9]")
	private Integer ssWorkRequestCreateFlg;

	/**
	 * 次回契約更新可能フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "次回契約更新可能フラグ", required = false, position = 97, allowableValues = "range[0,9]")
	private Integer nextUpdatePossibleFlg;

	/**
	 * 次回自動更新フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "次回自動更新フラグ", required = false, position = 98, allowableValues = "range[0,9]")
	private Integer nextAutoUpdateFlg;

	/**
	 * ARCS期間売保守元契約処理状態
	 */
	@ApiModelProperty(value = "ARCS期間売保守元契約処理状態", required = false, position = 99, allowableValues = "未作成(\"0\"),CSV作成済み(\"1\"),対象外(\"2\")")
	private ArcsPeriodSaleMntOriginStatus arcsPeriodSaleMntOriginStatus;

	/**
	 * ARCS期間売保守元契約連携日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "ARCS期間売保守元契約連携日", required = false, position = 100)
	private Date arcsPeriodSaleMntOriginLinkAt;

	/**
	 * 次回継続可能機種なしフラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "次回継続可能機種なしフラグ", required = false, position = 101, allowableValues = "range[0,9]")
	private Integer nextEquipmentNotContFlg;

	/**
	 * 基本契約区分
	 */
	@ApiModelProperty(value = "基本契約区分", required = false, position = 102, allowableValues = "基本契約(\"1\"),基本契約_一部(\"2\")")
	private BasicContractDiv basicContractDiv;

	/**
	 * 基本契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "基本契約ID", required = false, position = 103, allowableValues = "range[0,9223372036854775807]")
	private Long basicContractId;

	/**
	 * 契約状態遷移制御区分
	 */
	@ApiModelProperty(value = "契約状態遷移制御区分", required = false, position = 104, allowableValues = "手配の作業完了をもって契約状態を進める(\"0\"),手配の作業完了を待たずに契約状態を進める(\"1\")")
	private ContractStatusControlType contractStatusControlType;

	/**
	 * 納入日
	 */
	@ApiModelProperty(value = "納入日", required = false, position = 105)
	@Temporal(TemporalType.DATE)
	private Date deliveryDate;

	/**
	 * サービス終了最大延長日
	 */
	@ApiModelProperty(value = "サービス終了最大延長日", required = false, position = 106)
	@Temporal(TemporalType.DATE)
	private Date serviceTermMaxEnd;

	/**
	 * 延長可能契約月数
	 */
	@ApiModelProperty(value = "延長可能契約月数", required = false, position = 107)
	private Long maxExtensionMonths;

	/**
	 * 更新用課金開始日
	 */
	@ApiModelProperty(value = "更新用課金開始日", required = false, position = 108)
	@Temporal(TemporalType.DATE)
	private Date billingStartDateForUpdate;

	/**
	 * 計上期間明細
	 */
	@Valid
	@OneToMany(mappedBy = "contract")
	@OrderBy("item_master_id ASC, accounting_period_start ASC")
	@ApiModelProperty(value = "計上期間明細", required = false, position = 107)
	private List<AccountingPeriodDetail> accountingPeriodDetailList;

}
