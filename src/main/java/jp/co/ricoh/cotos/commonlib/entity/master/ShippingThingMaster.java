package jp.co.ricoh.cotos.commonlib.entity.master;

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
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "発送物ありマスタID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 品種マスタ
	 */
	@ManyToOne(optional = false)
	@JsonIgnore
	@JoinColumn(name = "item_master_id", referencedColumnName = "id")
	@Schema(description = "品種マスタ", requiredMode = Schema.RequiredMode.REQUIRED)
	private ItemMaster itemMaster;

	/**
	 * 物あり品種コード
	 */
	@Size(max = 255)
	@Schema(description = "物あり品種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String thingItemCode;

	/**
	 * 発送機器名称
	 */
	@Size(max = 255)
	@Schema(description = "発送機器名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String shippingMachineName;

	/**
	 * デフォルト数量
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "デフォルト数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer defaultQuantity;

	/**
	 * 原価
	 */
	@Digits(integer = 19, fraction = 2)
	@DecimalMax("9999999999999999999.99")
	@Schema(description = "原価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal price;

	/**
	 * 発送区分
	 */
	@Column(nullable = false)
	@Schema(description = "発送区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "NW機器経由(\"0\"), 直送(\"1\"), 自課所(\"2\")")
	private ShippingType shippingType;

	/**
	 * 発注フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "発注フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private int orderFlg;

	/**
	 * 有効期間(開始日)
	 */
	@Schema(description = "有効期間(開始日)", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date availablePeriodFrom;

	/**
	 * 有効期間(終了日)
	 */
	@Schema(description = "有効期間(終了日)", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date availablePeriodTo;

	/**
	 * FFM内部振替除外フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "FFM内部振替除外フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private int ffmInsideTransExclusionFlg;

	/**
	 * プロダクト確認集計表フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "プロダクト確認集計表フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private int productSpreadsheetFlg;

	/**
	 * 仕入先コード
	 */
	@Size(max = 255)
	@Schema(description = "仕入先コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String vendorCode;
}