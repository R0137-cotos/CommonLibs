package jp.co.ricoh.cotos.commonlib.entity.master;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 品種を表すEntity
 */
@Entity
@Data
@ToString(exclude = { "productMaster" })
@EqualsAndHashCode(callSuper = true)
@Table(name = "item_master")
public class ItemMaster extends EntityBaseMaster {

	@Description(value = "品種区分")
	public enum ItemType {

		なし("0"), 基本("1"), オプション("2");

		private final String text;

		private ItemType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ItemType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "費用種別")
	public enum CostType {

		初期費("1"), 月額_定額("2"), 年額("3"), 月額_従量("4"), 違約金("5");

		private final String text;

		private CostType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static CostType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "契約期間起算日区分")
	public enum ContractSpanStartDateType {

		契約日("1"), サービス開始日("2"), サービス開始翌月１日("3");

		private final String text;

		private ContractSpanStartDateType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ContractSpanStartDateType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "違約金起算日区分")
	public enum PenaltyStartDateType {

		課金開始日("1");

		private final String text;

		private PenaltyStartDateType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static PenaltyStartDateType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "分解後品種区分")
	public enum ItemDecomposeType {

		通常("1"), 分解前("2"), 分解後("3");

		private final String text;

		private ItemDecomposeType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ItemDecomposeType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "HW/NOS区分")
	public enum HwNosType {

		HW("1"), NOS("2");

		private final String text;

		private HwNosType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static HwNosType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "サービス利用希望日設定可能区分")
	public enum ServicePreferredSettingPossibleType {

		// null:制限なし
		営業日のみ("1"), 営業日と土曜日("2");

		private final String text;

		private ServicePreferredSettingPossibleType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ServicePreferredSettingPossibleType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "契約期間区分")
	public enum ContractSpanType {

		月契約("1"), 年契約("2");

		private final String text;

		private ContractSpanType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ContractSpanType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_master_seq")
	@SequenceGenerator(name = "item_master_seq", sequenceName = "item_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "品種マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "商品マスタ", required = true, position = 2)
	private ProductMaster productMaster;

	/**
	 * 品種名
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "品種名", required = true, position = 3, allowableValues = "range[0,255]")
	private String itemName;

	/**
	 * リコー品種コード
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "リコー品種コード", required = true, position = 4, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 品種区分
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "品種区分", required = true, allowableValues = "なし(\"0\"), 基本(\"1\"), オプション(\"2\")", example = "1", position = 5)
	private ItemType itemType;

	/**
	 * 費用種別
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "費用種別", required = true, allowableValues = "初期費(\"1\"), 月額_定額(\"2\"), 年額(\"3\"), 月額_従量(\"4\"), 違約金(\"5\")", example = "1", position = 6)
	private CostType costType;

	/**
	 * 仕切価格
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "仕切価格", required = true, position = 7, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal partitionPrice;

	/**
	 * 積上げ可能期間（開始日）
	 */
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "積上げ可能期間（開始日）", required = true, position = 8, allowableValues = "range[0,19]")
	private Date effectiveFrom;

	/**
	 * 積上げ可能期間（終了日）
	 */
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "積上げ可能期間（終了日）", required = true, position = 9, allowableValues = "range[0,19]")
	private Date effectiveTo;

	/**
	 * 仕入取引先コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "仕入取引先コード", required = false, position = 10, allowableValues = "range[0,255]")
	private String bpCd;

	/**
	 * Ｒ原価
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "Ｒ原価", required = false, position = 11, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rCost;

	/**
	 * ＳＡ仕切価格
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "ＳＡ仕切価格", required = false, position = 12, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rjPurchasePrice;

	/**
	 * ＲＪ仕切価格
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "ＲＪ仕切価格", required = false, position = 13, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rjDividingPrice;

	/**
	 * 母店売価(接点店仕切)
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "母店売価(接点店仕切)", required = false, position = 14, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal motherStorePrice;

	/**
	 * 消費税区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "消費税区分", required = false, position = 15, allowableValues = "range[0,255]")
	private String taxFlag;

	/**
	 * IFS連携フラグ
	 */
	@Max(9)
	@ApiModelProperty(value = "IFS連携フラグ", required = false, position = 16, allowableValues = "range[0,9]")
	private Integer ifsLinkageFlg;

	/**
	 * 最短納期日数
	 */
	@Max(99)
	@ApiModelProperty(value = "最短納期日数", required = false, position = 17, allowableValues = "range[0,99]")
	private Integer shortestDeliveryDate;

	/**
	 * 標準価格
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "標準価格", required = false, position = 18, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal standardPrice;

	/**
	 * 申込書帳票出力無しフラグ
	 */
	@Max(9)
	@ApiModelProperty(value = "申込書帳票出力無しフラグ", required = false, position = 19, allowableValues = "range[0,9]")
	private Integer noApplicationFormOutputFlg;

	/**
	 * 作業完了報告書出力無しフラグ
	 */
	@Max(9)
	@ApiModelProperty(value = "作業完了報告書出力無しフラグ", required = false, position = 20, allowableValues = "range[0,9]")
	private Integer noWorkReportOutputFlg;

	/**
	 * 計上分解構成マスタ
	 */
	@OneToMany(mappedBy = "itemMaster")
	@ApiModelProperty(value = "計上分解構成マスタ", required = false, position = 21)
	private List<RecordDecomposeCompMaster> recordDecomposeCompMasterList;

	/**
	 * 手配業務構成マスタ
	 */
	@OneToMany(mappedBy = "itemMaster")
	@ApiModelProperty(value = "手配業務構成マスタ", required = false, position = 22)
	private List<ArrangementWorkCompMaster> arrangementWorkCompMasterList;

	/**
	 * 機種構成マスタ
	 */
	@OneToMany(mappedBy = "itemMaster")
	@ApiModelProperty(value = "機種構成マスタ", required = false, position = 23)
	private List<EquipmentCompMaster> equipmentCompMasterList;

	/**
	 * 品種振替構成マスタ
	 */
	@OneToMany(mappedBy = "itemMaster")
	@ApiModelProperty(value = "品種振替構成マスタ", required = false, position = 24)
	private List<ItemTransCompMaster> itemTransCompMasterList;

	/**
	 * メーカー商品コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メーカー商品コード", required = false, position = 25, allowableValues = "range[0,255]")
	private String makerItemCode;

	/**
	 * 提供終了日
	 */
	@ApiModelProperty(value = "提供終了日", required = false, position = 26)
	@Temporal(TemporalType.DATE)
	private Date offerEndDate;

	/**
	 * 新規受注停止日
	 */
	@ApiModelProperty(value = "新規受注停止日", required = false, position = 27)
	@Temporal(TemporalType.DATE)
	private Date newOrderStopDate;

	/**
	 * 最終連携月
	 */
	@ApiModelProperty(value = "最終連携月", required = false, position = 28)
	@Temporal(TemporalType.DATE)
	private Date finalLinkedMonth;

	/**
	 * 値引き下限値
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "値引き下限値", required = false, position = 29, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal lowerLimit;

	/**
	 * V-UP連携除外フラグ
	 */
	@Max(9)
	@ApiModelProperty(value = "V-UP連携除外フラグ", required = false, position = 30, allowableValues = "range[0,9]")
	private Integer vupLinkageExclusionFlg;

	/**
	 * ベンダー略称
	 */
	@ApiModelProperty(value = "ベンダー略称", required = false, position = 31)
	private String vendorShortName;

	/**
	 * ＲＪ販事本仕入価格
	 */
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "ＲＪ販事本仕入価格", required = false, position = 32, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rjHanjihonPurchasePrice;

	/**
	 * 見積承認ルートグループマスタ
	 */
	@ManyToOne
	@JoinColumn(name = "estimation_approval_route_grp_id", referencedColumnName = "id")
	@ApiModelProperty(value = "承認ルートグループマスタ（見積）", required = false, position = 33)
	private ApprovalRouteGrpMaster estimationApprovalRouteGrpMaster;

	/**
	 * 契約承認ルートグループマスタ
	 */
	@ManyToOne
	@JoinColumn(name = "contract_approval_route_grp_id", referencedColumnName = "id")
	@ApiModelProperty(value = "承認ルートグループマスタ（契約）", required = false, position = 34)
	private ApprovalRouteGrpMaster contractApprovalRouteGrpMaster;

	/**
	 * 品種別チェック項目マスタ
	 */
	@ManyToOne
	@JoinColumn(name = "check_by_item_master_id", referencedColumnName = "id")
	@ApiModelProperty(value = "品種別チェック項目マスタ", required = false, position = 35)
	private CheckByItemMaster checkByItemMaster;

	/**
	 * 契約自動締結除外フラグ
	 */
	@Max(9)
	@ApiModelProperty(value = "契約自動締結除外フラグ", required = false, position = 36, allowableValues = "range[0,9]")
	private Integer contractAutoSigningExclusionFlg;

	/**
	 * 小数点単価フラグ
	 */
	@Max(9)
	@ApiModelProperty(value = "小数点単価フラグ", required = false, position = 37, allowableValues = "range[0,9]")
	private Integer decimalUnitPriceFlg;

	/**
	 * 契約期間月数
	 */
	@Max(99999)
	@Min(0)
	@ApiModelProperty(value = "契約期間月数", required = false, position = 37, allowableValues = "range[0,99999]")
	private Integer contractSpanMonth;

	/**
	 * 契約期間起算日区分
	 */
	@ApiModelProperty(value = "契約期間起算日区分", required = false, position = 38, allowableValues = "サービス開始日(\"1\"), サービス開始翌月1日(\"2\")")
	private ContractSpanStartDateType contractSpanStartDateType;

	/**
	 * 分解元品種マスタ
	 */
	@ManyToOne
	@JoinColumn(name = "origin_item_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "分解元品種マスタ", required = false, position = 39)
	private ItemMaster originItemMaster;

	/**
	 * 違約金有無フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "違約金有無フラグ", required = false, position = 40, allowableValues = "range[0,9]")
	private Integer penaltyFlg;

	/**
	 * 最低契約月数
	 */
	@Max(99999)
	@Min(0)
	@ApiModelProperty(value = "最低契約月数", required = false, position = 41, allowableValues = "range[0,99999]")
	private Integer minContractMonths;

	/**
	 * 違約金起算日区分
	 */
	@ApiModelProperty(value = "違約金起算日区分", required = false, position = 42, allowableValues = "サービス開始日(\"1\"), サービス開始翌月1日(\"2\")")
	private PenaltyStartDateType penaltyStartDateType;

	/**
	 * 違約金品種マスタ
	 */
	@ManyToOne
	@JoinColumn(name = "penalty_item_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "違約金品種マスタ", required = false, position = 43)
	private ItemMaster penaltyItemMaster;

	/**
	 * 分解後品種区分
	 */
	@ApiModelProperty(value = "分解後品種区分", required = false, position = 44, allowableValues = "通常(\"1\"), 分解前(\"2\"), 分解後(\"3\")")
	private ItemDecomposeType itemDecomposeType;

	/**
	 * ランニング計上開始日日付計算パターンマスタ
	 */
	@ManyToOne
	@JoinColumn(name = "running_from_calc_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "ランニング計上開始日日付計算パターンマスタ", required = false, position = 45)
	private DateCalcPatternMaster dateCalcPatternMaster;

	/**
	 * 品種分解マスタ
	 */
	@Valid
	@OneToMany(mappedBy = "itemMaster")
	@ApiModelProperty(value = "品種分解マスタ", required = false, position = 46)
	private List<ItemDecomposeMaster> itemDecomposeMasterList;

	/**
	 * 発送物ありマスタ
	 */
	@Valid
	@OneToMany(mappedBy = "itemMaster")
	@ApiModelProperty(value = "発送物ありマスタ", required = false, position = 47)
	private List<ShippingThingMaster> shippingThingMasterList;

	/**
	 * 品種ライセンス用設定マスタ
	 */
	@Valid
	@OneToMany(mappedBy = "itemMaster")
	@ApiModelProperty(value = "品種ライセンス用設定マスタ", required = false, position = 48)
	private List<ItemLicenseSettingMaster> ItemLicenseSettingMasterList;

	/**
	 * HW/NOS区分
	 */
	@ApiModelProperty(value = "HW/NOS区分", required = false, position = 49, allowableValues = "HW(\"1\"), NOS(\"2\")")
	private HwNosType hwNosType;

	/**
	 * メール基本契約商品表示フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "メール基本契約商品表示フラグ", required = false, position = 50, allowableValues = "range[0,9]")
	private Integer mailBasicContractProductDispFlg;

	/**
	 * 契約更新品種マスタ
	 */
	@ManyToOne
	@JoinColumn(name = "contract_update_item_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "契約更新品種マスタ", required = false, position = 51)
	private ItemMaster contractUpdateItemMaster;

	/**
	 * サービス利用希望日設定可能区分
	 */
	@ApiModelProperty(value = "サービス利用希望日設定可能区分", required = false, position = 52, allowableValues = "制限なし(null),営業日のみ(\"1\"), 営業日と土曜日(\"2\")")
	private ServicePreferredSettingPossibleType servicePreferredSettingPossibleType;

	/**
	 * 拡張項目
	 */
	@ApiModelProperty(value = "拡張項目", required = false, position = 53)
	@Lob
	private String extendsParameter;

	/**
	 * オンサイトweb申込フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "オンサイトweb申込フラグ", required = false, position = 54, allowableValues = "range[0,9]")
	private Integer onsiteWebApplicationFlg;

	/**
	 * 契約期間区分
	 */
	@ApiModelProperty(value = "契約期間区分", required = false, position = 55, allowableValues = "月契約(\"1\"), 年契約(\"2\")")
	private ContractSpanType contractSpanType;

	/**
	 * 品種グループマスタID
	 */
	@ManyToOne
	@JoinColumn(name = "item_group_master_id", referencedColumnName = "id")
	@ApiModelProperty(value = "品種グループマスタID", required = false, position = 56)
	private ItemGroupMaster itemGroupMaster;

	/**
	 * 商材固有品種グループマスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "商材固有品種グループマスタID", required = false, position = 57, allowableValues = "range[0,9223372036854775807]")
	private Long specificItemGroupMasterId;

	/**
	 * 特殊加算月数
	 */
	@Max(99999)
	@Min(0)
	@ApiModelProperty(value = "特殊加算月数", required = false, position = 58, allowableValues = "range[0,99999]")
	private Integer specialAddMonths;

	/**
	 * 紛失金品種マスタ
	 */
	@ManyToOne
	@JoinColumn(name = "lost_item_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "紛失金品種マスタ", required = false, position = 59)
	private ItemMaster lostItemMaster;

	/**
	 * 破損水没金品種マスタ
	 */
	@ManyToOne
	@JoinColumn(name = "damage_item_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "破損水没金品種マスタ", required = false, position = 60)
	private ItemMaster damageItemMaster;

}
