package jp.co.ricoh.cotos.commonlib.entity.master;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 発送物ありマスタ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "shipping_thing_master")
public class ShippingThingMaster extends EntityBase {

	public enum ShippingType {
		NW機器経由("0"), 直送("1"), 自課所("2");

		private final String text;

		private ShippingType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ShippingType fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipping_thing_master_seq")
	@SequenceGenerator(name = "shipping_thing_master_seq", sequenceName = "shipping_thing_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "配送仕分け用郵便番マスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 品種マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "品種マスタID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long itemMasterId;

	/**
	 * 物あり品種コード
	 */
	@Min(0)
	@ApiModelProperty(value = "物あり品種コード", required = true, position = 3, allowableValues = "range[0,9223372036854775807]")
	private long thingItemCode;

	/**
	 * 発送機器名称
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "発送機器名称", required = false, position = 4, allowableValues = "range[0,255]")
	private String shippingMachineName;

	/**
	 * デフォルト数量
	 */
	@Max(99999)
	@Min(0)
	@ApiModelProperty(value = "デフォルト数量", required = true, position = 5, allowableValues = "range[0,99999]")
	private int defaultQuantity;

	/**
	 * 原価
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "原価", required = false, position = 6, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal price;

	/**
	 * 発送区分
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "発送区分", required = true, allowableValues = "NW機器経由(\"0\"), 直送(\"1\"), 自課所(\"2\")", example = "0", position = 7, readOnly = false)
	private ShippingType shippingType;

	/**
	 * 発注フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "発注フラグ", required = true, position = 8, allowableValues = "range[0,9]", readOnly = true)
	private int orderFlg;

	/**
	 * 有効期間(開始日)
	 */
	@ApiModelProperty(value = "有効期間(開始日)", required = false, position = 9)
	@Temporal(TemporalType.DATE)
	private Date availablePeriodFrom;

	/**
	 * 有効期間(終了日)
	 */
	@ApiModelProperty(value = "有効期間(終了日)", required = false, position = 10)
	@Temporal(TemporalType.DATE)
	private Date availablePeriodTo;

	/**
	 * FFM内部振替除外フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "FFM内部振替除外フラグ", required = true, position = 11, allowableValues = "range[0,9]", readOnly = true)
	private int ffmInsideTransExclusionFlg;

	/**
	 * プロダクト確認集計表フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "プロダクト確認集計表フラグ", required = true, position = 12, allowableValues = "range[0,9]", readOnly = true)
	private int productSpreadsheetFlg;

	/**
	 * FFM連携納期計算マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "FFM連携納期計算マスタID", required = true, position = 13, allowableValues = "range[0,9223372036854775807]")
	private long ffmDeliveryDateCalcMasterId;
}
