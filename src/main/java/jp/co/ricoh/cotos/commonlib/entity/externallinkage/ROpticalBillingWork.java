package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

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
 * リコーひかり請求データWORKを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "r_optical_billing_work")
public class ROpticalBillingWork extends EntityBase {

	@Description(value = "東西種別")
	public enum EastWestDiv {

		東日本("1"), 西日本("2");

		private final String text;

		private EastWestDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static EastWestDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * リコーひかり請求データWorkID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "r_optical_billing_work_seq")
	@SequenceGenerator(name = "r_optical_billing_work_seq", sequenceName = "r_optical_billing_work_seq", allocationSize = 1)
	@ApiModelProperty(value = "リコーひかり請求データWorkID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
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
	 * 東西種別
	 */
	@ApiModelProperty(value = "東西種別", required = false, position = 6, allowableValues = "東日本(\"1\"), 西日本(\"2\")")
	private EastWestDiv eastWestDiv;

	/**
	 * 算定年月
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "算定年月", required = false, position = 7)
	private Date processDate;

	/**
	 * 疑似内訳コード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "疑似内訳コード", required = false, position = 8, allowableValues = "range[0,255]")
	private String breakdownCode;

	/**
	 * 疑似内訳名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "疑似内訳名", required = false, position = 9, allowableValues = "range[0,255]")
	private String breakdownName;

	/**
	 * 内訳金額
	 */
	@DecimalMin("0.00")
	@Digits(integer = 19, fraction = 2)
	@ApiModelProperty(value = "内訳金額", required = false, position = 10, allowableValues = "range[0.00,9999999999999999999.99]")
	private BigDecimal billingBreakdown;

	/**
	 * 利用開始日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "利用開始日", required = false, position = 11)
	private Date startDate;

	/**
	 * 利用終了日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "利用終了日", required = false, position = 12)
	private Date endDate;

	/**
	 * ファイル取込管理ID
	 */
	@Min(0)
	@ApiModelProperty(value = "ファイル取込管理ID", required = false, position = 13, allowableValues = "range[0,9223372036854775807]")
	private long fileImportManagementId;

}
