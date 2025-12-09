package jp.co.ricoh.cotos.commonlib.entity.externallinkage;

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
	@Schema(description = "リコーひかり請求データID(作成時不要)", allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * CSVファイル名
	 */
	@Size(max = 255)
	@Schema(description = "CSVファイル名", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,255]")
	private String csvFilename;

	/**
	 * CSVファイル区分
	 */
	@Size(max = 255)
	@Schema(description = "CSVファイル区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String fileKindTypeDiv;

	/**
	 * CSV行ID
	 */
	@Min(0)
	@Schema(description = "CSV行ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long csvId;

	/**
	 * CAF番号
	 */
	@Size(max = 255)
	@Schema(description = "CAF番号", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String cafNo;

	/**
	 * 契約ID
	 */
	@Min(0)
	@Schema(description = "契約ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long contractId;

	/**
	 * 算定年月
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "算定年月", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date processDate;

	/**
	 * 内訳金額
	 */
	@Digits(integer = 19, fraction = 2)
	@Schema(description = "内訳金額", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private BigDecimal billingBreakdown;

	/**
	 * リコーひかり請求情報変換マスタID
	 */
	@Min(0)
	@Schema(description = "リコーひかり請求情報変換マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long rOpticalTransferMasterId;

	/**
	 * リコー品種コード
	 */
	@Column(nullable = false)
	@Schema(description = "リコー品種コード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String ricohItemCode;

	/**
	 * 計上テーブル挿入区分
	 */
	@Schema(description = "計上テーブル挿入区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未済(\"0\"), 済(\"1\"), エラー(\"9\")")
	private InsertAccountingFlg insertAccountingFlg;

}
