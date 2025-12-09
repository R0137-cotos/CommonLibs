package jp.co.ricoh.cotos.commonlib.entity.master;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.ContractSpanType;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.CostType;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.ItemType;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.ServicePreferredSettingPossibleType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 品種Dto
 * 子エンティティは持たない
 */
@Entity
@Data
@ToString(exclude = { "productMasterDto" })
@EqualsAndHashCode(callSuper = true)
@Table(name = "item_master")
public class ItemMasterDto extends EntityBaseMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_master_seq")
	@SequenceGenerator(name = "item_master_seq", sequenceName = "item_master_seq", allocationSize = 1)
	@Schema(description = "品種マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "商品マスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private ProductMasterDto productMasterDto;

	/**
	 * 品種名
	 */
	@Column(nullable = false)
	@Schema(description = "品種名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String itemName;

	/**
	 * リコー品種コード
	 */
	@Column(nullable = false)
	@Schema(description = "リコー品種コード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 品種区分
	 */
	@Column(nullable = false)
	@Schema(description = "品種区分", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "なし(\"0\"), 基本(\"1\"), オプション(\"2\")", example = "1")
	private ItemType itemType;

	/**
	 * 費用種別
	 */
	@Column(nullable = false)
	@Schema(description = "費用種別", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "初期費(\"1\"), 月額_定額(\"2\"), 年額(\"3\"), 月額_従量(\"4\")", example = "1")
	private CostType costType;

	/**
	 * 仕切価格
	 */
	@Column(nullable = false)
	@Schema(description = "仕切価格", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal partitionPrice;

	/**
	 * 積上げ可能期間（開始日）
	 */
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "積上げ可能期間（開始日）", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,19]")
	private Date effectiveFrom;

	/**
	 * 積上げ可能期間（終了日）
	 */
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "積上げ可能期間（終了日）", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,19]")
	private Date effectiveTo;

	/**
	 * 仕入取引先コード
	 */
	@Size(max = 255)
	@Schema(description = "仕入取引先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String bpCd;

	/**
	 * Ｒ原価
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "Ｒ原価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rCost;

	/**
	 * ＳＡ仕切価格
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "ＳＡ仕切価格", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rjPurchasePrice;

	/**
	 * ＲＪ仕切価格
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "ＲＪ仕切価格", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rjDividingPrice;

	/**
	 * 母店売価(接点店仕切)
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "母店売価(接点店仕切)", allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal motherStorePrice;

	/**
	 * 消費税区分
	 */
	@Size(max = 255)
	@Schema(description = "消費税区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String taxFlag;

	/**
	 * IFS連携フラグ
	 */
	@Max(9)
	@Schema(description = "IFS連携フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer ifsLinkageFlg;

	/**
	 * 最短納期日数
	 */
	@Max(99)
	@Schema(description = "最短納期日数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99]")
	private Integer shortestDeliveryDate;

	/**
	 * 標準価格
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "標準価格", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal standardPrice;

	/**
	 * 申込書帳票出力無しフラグ
	 */
	@Max(9)
	@Schema(description = "申込書帳票出力無しフラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer noApplicationFormOutputFlg;

	/**
	 * 作業完了報告書出力無しフラグ
	 */
	@Max(9)
	@Schema(description = "作業完了報告書出力無しフラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer noWorkReportOutputFlg;

	/**
	 * メーカー商品コード
	 */
	@Size(max = 255)
	@Schema(description = "メーカー商品コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String makerItemCode;

	/**
	 * 提供終了日
	 */
	@Schema(description = "提供終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date offerEndDate;

	/**
	 * 新規受注停止日
	 */
	@Schema(description = "新規受注停止日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date newOrderStopDate;

	/**
	 * 最終連携月
	 */
	@Schema(description = "最終連携月", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date finalLinkedMonth;

	/**
	 * 値引き下限値
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "値引き下限値", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal lowerLimit;

	/**
	 * V-UP連携除外フラグ
	 */
	@Max(9)
	@Schema(description = "V-UP連携除外フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer vupLinkageExclusionFlg;

	/**
	 * ベンダー略称
	 */
	@Schema(description = "ベンダー略称", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String vendorShortName;

	/**
	 * ＲＪ販事本仕入価格
	 */
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "ＲＪ販事本仕入価格", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rjHanjihonPurchasePrice;

	/**
	 * サービス利用希望日設定可能区分
	 */
	@Schema(description = "サービス利用希望日設定可能区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "制限なし(null),営業日のみ(\"1\"), 営業日と土曜日(\"2\")")
	private ServicePreferredSettingPossibleType servicePreferredSettingPossibleType;

	/**
	 * 拡張項目
	 */
	@Schema(description = "拡張項目", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Lob
	private String extendsParameter;

	/**
	 * 契約期間区分
	 */
	@Schema(description = "契約期間区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "月契約(\"1\"), 年契約(\"2\")")
	private ContractSpanType contractSpanType;

	/**
	 * イニシャルランニング対応品種マスタID
	 */
	@Schema(description = "イニシャルランニング対応品種マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long initialRunningItemMasterId;

	/**
	 * 価格改定日マスタID
	 */
	@Schema(description = "価格改定日マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long priceRevisionDateMasterId;

	/**
	 * 売上可能開始日
	 */
	@Schema(description = "売上可能開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date accountPossibleStartDate;

	/**
	 * 価格改定前リコー品種コード
	 */
	@Schema(description = "価格改定前リコー品種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String bfPriceRevisionItemCode;

	/**
	 * 価格改定処理グループID
	 */
	@Schema(description = "価格改定処理グループID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long priceRevisionProcessGrpId;

	/**
	 * 月割品種対応初期費品種マスタID
	 */
	@Schema(description = "月割品種対応初期費品種マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long prorationLinkedInitialItemMasterId;

	/**
	 * 同一SS用最短納期日数
	 */
	@Max(99)
	@Schema(description = "同一SS用最短納期日数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99]")
	private Integer shortestDeliveryDateForSameSs;
}
