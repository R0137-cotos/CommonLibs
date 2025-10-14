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

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "ID", required = true, position = 1, allowableValues = "range[0,9999999999999999999]")
	private long id;

	/**
	 * 契約トランザクションID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "契約トランザクションID", required = true, position = 2, allowableValues = "range[0,9999999999999999999]")
	private long contractTranId;

	/**
	 * RJ管理番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "RJ管理番号", required = false, position = 3, allowableValues = "range[0,255]")
	private String rjManageNumber;

	/**
	 * 契約No.
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "契約No.", required = false, position = 4, allowableValues = "range[0,255]")
	private String contractId;

	/**
	 * 商品コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "商品コード", required = false, position = 5, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 商品名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "商品名", required = false, position = 6, allowableValues = "range[0,255]")
	private String itemContractName;

	/**
	 * 回線番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "回線番号", required = false, position = 7, allowableValues = "range[0,255]")
	private String lineNumber;

	/**
	 * シリアル番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "シリアル番号", required = false, position = 8, allowableValues = "range[0,255]")
	private String serialNumber;

	/**
	 * 連携月
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "連携月", required = false, position = 9, allowableValues = "range[0,255]")
	private String createYm;

	/**
	 * 契約単価　（月額商品の単価）
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "契約単価　（月額商品の単価）", required = false, position = 10, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal orderPrice;

	/**
	 * 利用月
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "利用月", required = false, position = 11, allowableValues = "range[0,255]")
	private String billingDate;

	/**
	 * 数量
	 */
	@Max(99999)
	@ApiModelProperty(value = "数量", required = false, position = 12, allowableValues = "range[0,99999]")
	private Integer salesCnt;

	/**
	 * 契約単価　（月額商品の単価）
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "単価", required = false, position = 13, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal salesPrice;

	/**
	 * 税区分
	 */
	@ApiModelProperty(value = "税区分", required = false, position = 14, allowableValues = "range[0,255]")
	private InvoiceTaxType salesTaxType;

	/**
	 * 税率（％）
	 */
	@Max(99999)
	@ApiModelProperty(value = "税率（％）", required = false, position = 15, allowableValues = "range[0.00,99999999999999999.99]")
	private Integer salesTaxRate;

	/**
	 * 消費税
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "消費税", required = false, position = 16, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal salesTaxPrice;

	/**
	 * 金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "金額", required = false, position = 17, allowableValues = "range[0.00,99999999999999999.99]")
	private BigDecimal remarks;

	/**
	 * 契約送信ステータス
	 */
	@ApiModelProperty(value = "契約送信ステータス", required = false, position = 18, allowableValues = "range[0,255]")
	private BatchCommonStatus sendStatus;

	/**
	 * 契約送信日
	 */
	@ApiModelProperty(value = "契約送信日", required = false, position = 19)
	@Temporal(TemporalType.DATE)
	private Date sendDate;

	/**
	 * 請求受信ステータス
	 */
	@ApiModelProperty(value = "請求受信ステータス", required = false, position = 20, allowableValues = "range[0,255]")
	private BatchCommonStatus receiveStatus;

	/**
	 * 請求受信日
	 */
	@ApiModelProperty(value = "請求受信日", required = false, position = 21)
	@Temporal(TemporalType.DATE)
	private Date receiveDate;

	/**
	 * 計上ステータス
	 */
	@ApiModelProperty(value = "計上ステータス", required = false, position = 22, allowableValues = "range[0,255]")
	private BatchCommonStatus accountingStatus;

	/**
	 * 計上処理日
	 */
	@ApiModelProperty(value = "計上処理日", required = false, position = 23)
	@Temporal(TemporalType.DATE)
	private Date accountingDate;

	/**
	 * 商品種類区分
	 */
	@ApiModelProperty(value = "商品種類区分", required = false, position = 24, allowableValues = "range[0,255]")
	private String productClassDiv;

}
