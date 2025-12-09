package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "リコーひかり請求データWorkID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * CSVファイル名
	 */
	@Size(max = 255)
	@Schema(description = "CSVファイル名", required = true, allowableValues = "range[0,255]")
	private String csvFilename;

	/**
	 * CSVファイル区分
	 */
	@Size(max = 255)
	@Schema(description = "CSVファイル区分", required = false, allowableValues = "range[0,255]")
	private String fileKindTypeDiv;

	/**
	 * CSV行ID
	 */
	@Min(0)
	@Schema(description = "CSV行ID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long csvId;

	/**
	 * CAF番号
	 */
	@Size(max = 255)
	@Schema(description = "CAF番号", required = false, allowableValues = "range[0,255]")
	private String cafNo;

	/**
	 * 東西種別
	 */
	@Schema(description = "東西種別", required = false, allowableValues = "東日本(\"1\"), 西日本(\"2\")")
	private EastWestDiv eastWestDiv;

	/**
	 * 算定年月
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "算定年月", required = false)
	private Date processDate;

	/**
	 * 疑似内訳コード
	 */
	@Size(max = 255)
	@Schema(description = "疑似内訳コード", required = false, allowableValues = "range[0,255]")
	private String breakdownCode;

	/**
	 * 疑似内訳名
	 */
	@Size(max = 255)
	@Schema(description = "疑似内訳名", required = false, allowableValues = "range[0,255]")
	private String breakdownName;

	/**
	 * 内訳金額
	 */
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "内訳金額", required = false)
	private BigDecimal billingBreakdown;

	/**
	 * 利用開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "利用開始日", required = false)
	private Date startDate;

	/**
	 * 利用終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "利用終了日", required = false)
	private Date endDate;

	/**
	 * ファイル取込管理ID
	 */
	@Min(0)
	@Schema(description = "ファイル取込管理ID", required = false, allowableValues = "range[0,9223372036854775807]")
	private long fileImportManagementId;

	/**
	 * 変換元疑似内訳コード
	 */
	@Size(max = 255)
	@Schema(description = "変換元疑似内訳コード", required = false, allowableValues = "range[0,255]")
	private String convertFromBreakdownCode;
}
