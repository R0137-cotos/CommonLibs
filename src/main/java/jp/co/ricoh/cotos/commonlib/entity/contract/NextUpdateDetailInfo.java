package jp.co.ricoh.cotos.commonlib.entity.contract;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.DetailStatus;
import jp.co.ricoh.cotos.commonlib.entity.estimation.EstimationDetail.IncreaseDecreaseDiv;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.CostType;
import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.ItemType;
import jp.co.ricoh.cotos.commonlib.serializer.UnixTimestampDateSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 次回更新明細情報を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "next_update_detail_info")
public class NextUpdateDetailInfo extends EntityBase {

	@Description(value = "ベンダー連携状態")
	public enum VendorLinkageStatus {

		未連携("1"), 連携済("2"), 対象外("3");

		private final String text;

		private VendorLinkageStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static VendorLinkageStatus fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * 次回更新明細ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "next_update_detail_info_seq")
	@SequenceGenerator(name = "next_update_detail_info_seq", sequenceName = "next_update_detail_info_seq", allocationSize = 1)
	@Schema(description = "次回更新明細ID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 契約
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "契約", requiredMode = Schema.RequiredMode.REQUIRED)
	private Contract contract;

	/**
	 * 商品マスタID
	 */
	@Min(0)
	@Column(nullable = false)
	@Schema(description = "商品マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long productMasterId;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@Column(nullable = false)
	@Schema(description = "品種マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * 品種名
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "品種名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String itemContractName;

	/**
	 * リコー品種コード
	 */
	@Column(nullable = false)
	@NotNull
	@Size(max = 255)
	@Schema(description = "リコー品種コード", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 品種区分
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "品種区分", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "なし(\"0\"), 基本(\"1\"), オプション(\"2\")", example = "1")
	private ItemType itemType;

	/**
	 * 費用種別
	 */
	@NotNull
	@Schema(description = "費用種別", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "初期費(\"1\"), 月額(\"2\"), 年額(\"3\"), 月額_従量(\"4\"), 違約金(\"5\")", example = "1")
	private CostType costType;

	/**
	 * 仕切価格
	 */
	@NotNull
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "仕切価格", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal partitionPrice;

	/**
	 * 仕入取引先コード
	 */
	@Size(max = 255)
	@Schema(description = "仕入取引先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String bpCd;

	/**
	 * Ｒ原価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "Ｒ原価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rCost;

	/**
	 * ＳＡ仕切価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "ＳＡ仕切価格", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rjPurchasePrice;

	/**
	 * ＲＪ仕切価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "ＲＪ仕切価格", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rjDividingPrice;

	/**
	 * 母店売価(接点店仕切)
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "母店売価(接点店仕切)", allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal motherStorePrice;

	/**
	 * 消費税区分
	 */
	@Size(max = 255)
	@Schema(description = "消費税区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String taxFlag;

	/**
	 * メーカー商品コード
	 */
	@Size(max = 255)
	@Schema(description = "メーカー商品コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String makerItemCode;

	/**
	 * ＲＪ販事本仕入価格
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "ＲＪ販事本仕入価格", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal rjHanjihonPurchasePrice;

	/**
	 * 状態
	 */
	@Column(nullable = false)
	@NotNull
	@Schema(description = "状態", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "NOUPDATE(\"1\"), ADD(\"2\"), DELETE(\"3\"), UPDATE(\"4\")", example = "1")
	private DetailStatus state;

	/**
	 * 数量
	 */
	@Column(nullable = false)
	@Max(99999)
	@Min(-99999)
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[-99999,99999]")
	private int quantity;

	/**
	 * 単価
	 */
	@Column(nullable = false)
	@NotNull
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "単価", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal unitPrice;

	/**
	 * 金額
	 */
	@Column(nullable = false)
	@NotNull
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "金額", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal amountSummary;

	/**
	 * 摘要
	 */
	@Size(max = 255)
	@Schema(description = "摘要", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String detailAbstract;

	/**
	 * 変更前数量
	 */
	@Max(99999)
	@Min(-99999)
	@Schema(description = "変更前数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[-99999,99999]")
	private Integer beforeQuantity;

	/**
	 * 増減区分
	 */
	@Schema(description = "増減区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "増数(\"1\"), 減数(\"2\")", example = "1")
	private IncreaseDecreaseDiv increaseDecreaseDiv;

	/**
	 * 取込ファイルバージョン
	 */
	@Schema(description = "取込ファイルバージョン", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private Long importFileVersion;

	/**
	 * ベンダー連携状態
	 */
	@Schema(description = "状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未連携(\"1\"), 連携済(\"2\"), 対象外(\"3\")", example = "1")
	private VendorLinkageStatus vendorLinkageStatus;

	/**
	 * ベンダー連携日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = UnixTimestampDateSerializer.class)
	@Schema(description = "ベンダー連携日時(作成時不要)", readOnly = true)
	private Date vendorLinkageAt;

	/**
	 * 削除フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "削除フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer deleteFlg;
}