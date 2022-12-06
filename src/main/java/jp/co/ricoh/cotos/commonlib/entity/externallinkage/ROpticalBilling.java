package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * リコーひかり請求データを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "r_optical_billing")
public class ROpticalBilling extends EntityBase {

	@Description(value = "計上テーブル挿入区分")
	public enum InsertAccountingFlg {

		未済("0"), 済("1"), エラー("9");

		private final String text;

		private InsertAccountingFlg(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static InsertAccountingFlg fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * リコーひかり請求データID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "r_optical_billing_seq")
	@SequenceGenerator(name = "r_optical_billing_seq", sequenceName = "r_optical_billing_seq", allocationSize = 1)
	@ApiModelProperty(value = "リコーひかり請求データID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * CSVファイル名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "CSVファイル名", required = true, position = 2, allowableValues = "range[0,255]")
	private String csvFilename;

	/**
	 * CSVファイル区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "CSVファイル区分", required = false, position = 3, allowableValues = "range[0,255]")
	private String fileKindTypeDiv;

	/**
	 * CSV行ID
	 */
	@Min(0)
	@ApiModelProperty(value = "CSV行ID", required = false, position = 4, allowableValues = "range[0,9223372036854775807]")
	private Long csvId;

	/**
	 * CAF番号
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "CAF番号", required = false, position = 5, allowableValues = "range[0,255]")
	private String cafNo;

	/**
	 * 契約ID
	 */
	@Min(0)
	@ApiModelProperty(value = "契約ID", required = false, position = 6, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * 算定年月
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "算定年月", required = false, position = 7)
	private Date processDate;

	/**
	 * 内訳金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "内訳金額", required = false, position = 8, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal billingBreakdown;

	/**
	 * リコーひかり請求情報変換マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "リコーひかり請求情報変換マスタID", required = false, position = 9, allowableValues = "range[0,9223372036854775807]")
	private Long rOpticalTransferMasterId;

	/**
	 * リコー品種コード
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "リコー品種コード", required = false, position = 10, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 計上テーブル挿入区分
	 */
	@ApiModelProperty(value = "計上テーブル挿入区分", required = false, position = 11, allowableValues = "未済(\"1\"), 済(\"2\"), エラー(\"9\")")
	private InsertAccountingFlg insertAccountingFlg;

}
