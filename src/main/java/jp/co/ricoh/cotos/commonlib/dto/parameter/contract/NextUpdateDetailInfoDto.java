package jp.co.ricoh.cotos.commonlib.dto.parameter.contract;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.dto.parameter.common.DtoBase;
import lombok.Data;

/**
 * 次回更新明細登録のためのパラメータクラスを表します。
 */
@Data
public class NextUpdateDetailInfoDto extends DtoBase {

	/**
	 * 契約
	 */
	@Min(0)
	@Column(nullable = false)
	@ApiModelProperty(value = "契約ID", required = true, position = 1, allowableValues = "range[0,9223372036854775807]")
	private long contractId;

	/**
	 * 商品マスタID
	 */
	@Min(0)
	@Column(nullable = false)
	@ApiModelProperty(value = "商品マスタID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long productMasterId;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@Column(nullable = false)
	@ApiModelProperty(value = "品種マスタID", required = true, position = 3, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * 品種名
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@ApiModelProperty(value = "品種名", required = true, position = 4, allowableValues = "range[0,255]")
	private String itemContractName;

	/**
	 * リコー品種コード
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@ApiModelProperty(value = "リコー品種コード", required = true, position = 5, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 品種区分
	 */
	@Column(nullable = false)
	@NotNull
	@ApiModelProperty(value = "品種区分", required = true, allowableValues = "なし(\"0\"), 基本(\"1\"), オプション(\"2\")", example = "1", position = 6)
	private String itemType;

	/**
	 * 費用種別
	 */
	@NotNull
	@ApiModelProperty(value = "費用種別", required = false, allowableValues = "初期費(\"1\"), 月額(\"2\"), 年額(\"3\"), 月額_従量(\"4\"), 違約金(\"5\")", example = "1", position = 7)
	private String costType;

	/**
	 * 仕切価格
	 */
	@NotNull
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "仕切価格", required = false, position = 8, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal partitionPrice;

	/**
	 * 仕入取引先コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "仕入取引先コード", required = false, position = 9, allowableValues = "range[0,255]")
	private String bpCd;

	/**
	 * Ｒ原価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "Ｒ原価", required = false, position = 10, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rCost;

	/**
	 * ＳＡ仕切価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "ＳＡ仕切価格", required = false, position = 11, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rjPurchasePrice;

	/**
	 * ＲＪ仕切価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "ＲＪ仕切価格", required = false, position = 12, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rjDividingPrice;

	/**
	 * 母店売価(接点店仕切)
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "母店売価(接点店仕切)", required = false, position = 13, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal motherStorePrice;

	/**
	 * 消費税区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "消費税区分", required = false, position = 14, allowableValues = "range[0,255]")
	private String taxFlag;

	/**
	 * メーカー商品コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メーカー商品コード", required = false, position = 15, allowableValues = "range[0,255]")
	private String makerItemCode;

	/**
	 * ＲＪ販事本仕入価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "ＲＪ販事本仕入価格", required = false, position = 16, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rjHanjihonPurchasePrice;

	/**
	 * 状態
	 */
	@Column(nullable = false)
	@NotNull
	@ApiModelProperty(value = "状態", required = true, position = 17, allowableValues = "NOUPDATE(\"1\"), ADD(\"2\"), DELETE(\"3\"), UPDATE(\"4\")", example = "1")
	private String state;

	/**
	 * 数量
	 */
	@Column(nullable = false)
	@Max(99999)
	@Min(-99999)
	@ApiModelProperty(value = "数量", required = true, position = 18, allowableValues = "range[-99999,99999]")
	private int quantity;

	/**
	 * 単価
	 */
	@Column(nullable = false)
	@NotNull
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "単価", required = true, position = 19, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal unitPrice;

	/**
	 * 金額
	 */
	@Column(nullable = false)
	@NotNull
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "金額", required = true, position = 20, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal amountSummary;

	/**
	 * 摘要
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "摘要", required = false, position = 21, allowableValues = "range[0,255]")
	private String detailAbstract;

	/**
	 * 変更前数量
	 */
	@Max(99999)
	@Min(-99999)
	@ApiModelProperty(value = "変更前数量", required = false, position = 22, allowableValues = "range[-99999,99999]")
	private Integer beforeQuantity;

	/**
	 * 増減区分
	 */
	@ApiModelProperty(value = "増減区分", required = false, allowableValues = "増数(\"1\"), 減数(\"2\")", example = "1", position = 23)
	private String increaseDecreaseDiv;

	/**
	 * 取込ファイルバージョン
	 */
	@ApiModelProperty(value = "取込ファイルバージョン", required = false, position = 24, allowableValues = "range[0,9999999999999999999]")
	private Long importFileVersion;

	/**
	 * ベンダー連携状態
	 */
	@ApiModelProperty(value = "状態", required = false, allowableValues = "未連携(\"1\"), 連携済(\"2\"), 対象外(\"3\")", example = "1", position = 25)
	private String vendorLinkageStatus;

	/**
	 * ベンダー連携日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "ベンダー連携日時(作成時不要)", required = false, position = 26, readOnly = true)
	private Date vendorLinkageAt;

	/**
	 * 削除フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "削除フラグ", required = false, position = 27, allowableValues = "range[0,9]")
	private Integer deleteFlg;

}
