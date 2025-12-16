package jp.co.ricoh.cotos.commonlib.entity.contract.order;

import java.math.BigDecimal;
import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 注文商品情報
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "order_product_info")
public class OrderProductInfo extends EntityBase {

	@Description(value = "課金制約ルール")
	public enum ChargeRule {

		有料("0"), 初月無料("1"), 無料期間指定("2");

		private final String text;

		private ChargeRule(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ChargeRule fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "提供方法")
	public enum ProvideMethod {
		初期("1"), 月額("2"), 年額("3");

		private final String text;

		private ProvideMethod(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ProvideMethod fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_product_info_seq")
	@SequenceGenerator(name = "order_product_info_seq", sequenceName = "order_product_info_seq", allocationSize = 1)
	@Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED)
	private long id;

	/**
	 * 注文基本情報ID
	 */
	@OneToOne(optional = false)
	@JoinColumn(name = "order_basic_info_id", referencedColumnName = "id")
	@JsonIgnore
	private OrderBasicInfo orderBasicInfo;

	/**
	 * 商品コード（RICOH品種コード）
	 */
	@Column
	@Schema(description = "商品コード（RICOH品種コード）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String productCd;

	/**
	 * 課金制約ルール
	 */
	@Column
	@Schema(description = "課金制約ルール", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "有料(\"0\"), 初月無料(\"1\"), 無料期間指定(\"2\")")
	private ChargeRule chargeRule;

	/**
	 * 無料期間
	 */
	@Column
	@Schema(description = "無料期間", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String freePeriod;

	/**
	 * 商品名
	 */
	@Column
	@Schema(description = "商品名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String productName;

	/**
	 * 提供方法
	 */
	@Column
	@Schema(description = "提供方法", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "初期(\"1\"), 月額(\"2\"), 年額(\"3\")")
	private ProvideMethod provideMethod;

	/**
	 * 変更後数量
	 */
	@Column
	@Schema(description = "変更後数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer quantity;

	/**
	 * 変更前数量
	 */
	@Column
	@Schema(description = "変更前数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer beforeQuantity;

	/**
	 * 差分
	 */
	@Column
	@Schema(description = "差分", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Integer differenceQuantity;

	/**
	 * 売価単価
	 */
	@Column
	@Schema(description = "売価単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private BigDecimal unitPrice;

	/**
	 * 売価合計
	 */
	@Column
	@Schema(description = "売価合計", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private BigDecimal amountSummary;

	/**
	 * Bplats用ダミー商品コード（RICOH品種コード）
	 */
	@Column
	@Schema(description = "Bplats用ダミー商品コード（RICOH品種コード）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,]")
	private String bplatsDummyProductCd;

}
