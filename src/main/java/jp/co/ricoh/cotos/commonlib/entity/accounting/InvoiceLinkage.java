package jp.co.ricoh.cotos.commonlib.entity.accounting;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.BatchCommonStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * インボイス連携データ
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "invoice_linkage")
public class InvoiceLinkage extends EntityBase {

	@Description(value = "税区分")
	public enum InvoiceTaxType {

		対象外("0"), 外税("1"), 内税("2"), 免税("3"), 非課税("4");

		private final String text;

		private InvoiceTaxType(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static InvoiceTaxType fromString(String string) {
			if (StringUtils.isEmpty(string)) {
				return null;
			}
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_linkage_seq")
	@SequenceGenerator(name = "invoice_linkage_seq", sequenceName = "invoice_linkage_seq", allocationSize = 1)
	@Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 契約トランザクションID
	 */
	@Column(nullable = false)
	@Schema(description = "契約トランザクションID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9999999999999999999]")
	private long contractTranId;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@Schema(description = "RJ管理番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約No.
	 */
	@Size(max = 255)
	@Schema(description = "契約No.", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String contractId;

	/**
	 * 商品コード
	 */
	@Size(max = 255)
	@Schema(description = "商品コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 商品名
	 */
	@Size(max = 255)
	@Schema(description = "商品名", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String itemContractName;

	/**
	 * 回線番号
	 */
	@Size(max = 255)
	@Schema(description = "回線番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String lineNumber;

	/**
	 * シリアル番号
	 */
	@Size(max = 255)
	@Schema(description = "シリアル番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String serialNumber;

	/**
	 * 連携月
	 */
	@Size(max = 255)
	@Schema(description = "連携月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String createYm;

	/**
	 * 契約単価　（月額商品の単価）
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "契約単価　（月額商品の単価）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal orderPrice;

	/**
	 * 利用月
	 */
	@Size(max = 255)
	@Schema(description = "利用月", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String billingDate;

	/**
	 * 数量
	 */
	@Max(99999)
	@Schema(description = "数量", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,99999]")
	private Integer salesCnt;

	/**
	 * 契約単価　（月額商品の単価）
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "単価", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal salesPrice;

	/**
	 * 税区分
	 */
	@Schema(description = "税区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private InvoiceTaxType salesTaxType;

	/**
	 * 税率（％）
	 */
	@Max(99999)
	@Schema(description = "税率（％）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private Integer salesTaxRate;

	/**
	 * 消費税
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "消費税", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal salesTaxPrice;

	/**
	 * 金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal remarks;

	/**
	 * 契約送信ステータス
	 */
	@Schema(description = "契約送信ステータス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private BatchCommonStatus sendStatus;

	/**
	 * 契約送信日
	 */
	@Schema(description = "契約送信日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date sendDate;

	/**
	 * 請求受信ステータス
	 */
	@Schema(description = "請求受信ステータス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private BatchCommonStatus receiveStatus;

	/**
	 * 請求受信日
	 */
	@Schema(description = "請求受信日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date receiveDate;

	/**
	 * 計上ステータス
	 */
	@Schema(description = "計上ステータス", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private BatchCommonStatus accountingStatus;

	/**
	 * 計上処理日
	 */
	@Schema(description = "計上処理日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	@Temporal(TemporalType.DATE)
	private Date accountingDate;

	/**
	 * 商品種類区分
	 */
	@Schema(description = "商品種類区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String productClassDiv;

}
