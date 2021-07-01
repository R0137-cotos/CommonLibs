package jp.co.ricoh.cotos.commonlib.entity.master;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBaseMaster;
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
	@ApiModelProperty(value = "品種マスタID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 商品マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "商品マスタ", required = true, position = 2)
	private ProductMasterDto productMasterDto;

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
	@ApiModelProperty(value = "費用種別", required = true, allowableValues = "初期費(\"1\"), 月額_定額(\"2\"), 年額(\"3\"), 月額_従量(\"4\")", example = "1", position = 6)
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
	 * サービス利用希望日設定可能区分
	 */
	@ApiModelProperty(value = "サービス利用希望日設定可能区分", required = false, position = 33, allowableValues = "制限なし(null),営業日のみ(\"1\"), 営業日と土曜日(\"2\")")
	private ServicePreferredSettingPossibleType servicePreferredSettingPossibleType;
}
