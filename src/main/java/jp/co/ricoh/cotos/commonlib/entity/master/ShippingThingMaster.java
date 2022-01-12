package jp.co.ricoh.cotos.commonlib.entity.master;

import java.math.BigDecimal;
import java.util.Arrays;
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
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

	@Description(value = "発送区分")
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
	@ApiModelProperty(value = "発送物ありマスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 品種マスタ
	 */
	@ManyToOne(optional = false)
	@JsonIgnore
	@JoinColumn(name = "item_master_id", referencedColumnName = "id")
	@ApiModelProperty(value = "品種マスタ", required = true, position = 2)
	private ItemMaster itemMaster;

	/**
	 * 物あり品種コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "物あり品種コード", required = false, position = 3, allowableValues = "range[0,255]")
	private String thingItemCode;

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
	@ApiModelProperty(value = "デフォルト数量", required = false, position = 5, allowableValues = "range[0,99999]")
	private Integer defaultQuantity;

	/**
	 * 原価
	 */
	@Digits(integer = 19, fraction = 2)
	@DecimalMax("9999999999999999999.99")
	@ApiModelProperty(value = "原価", required = false, position = 6, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal price;

	/**
	 * 発送区分
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "発送区分", required = false, allowableValues = "NW機器経由(\"0\"), 直送(\"1\"), 自課所(\"2\")", position = 7)
	private ShippingType shippingType;

	/**
	 * 発注フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "発注フラグ", required = false, position = 8, allowableValues = "range[0,9]")
	private int orderFlg;

	/**
	 * 有効期間(開始日)
	 */
	@ApiModelProperty(value = "有効期間(開始日)", required = false, position = 9)
	@Temporal(TemporalType.TIMESTAMP)
	private Date availablePeriodFrom;

	/**
	 * 有効期間(終了日)
	 */
	@ApiModelProperty(value = "有効期間(終了日)", required = false, position = 10)
	@Temporal(TemporalType.TIMESTAMP)
	private Date availablePeriodTo;

	/**
	 * FFM内部振替除外フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "FFM内部振替除外フラグ", required = false, position = 11, allowableValues = "range[0,9]")
	private int ffmInsideTransExclusionFlg;

	/**
	 * プロダクト確認集計表フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "プロダクト確認集計表フラグ", required = false, position = 12, allowableValues = "range[0,9]")
	private int productSpreadsheetFlg;

	/**
	 * 仕入先コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "仕入先コード", required = false, position = 14, allowableValues = "range[0,255]")
	private String vendorCode;
}