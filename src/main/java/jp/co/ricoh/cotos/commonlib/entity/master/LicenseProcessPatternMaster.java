package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ライセンス工程パターンマスタを表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "license_process_pattern_master")
public class LicenseProcessPatternMaster extends EntityBase {

	@Description(value = "メール区分")
	public enum MailDiv {

		事前設定完了メール("1"), Welcameメール("2");

		private final String text;

		private MailDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static MailDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * ライセンス工程パターンマスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_process_pattern_master_seq")
	@SequenceGenerator(name = "license_process_pattern_master_seq", sequenceName = "license_process_pattern_master_seq", allocationSize = 1)
	@Schema(description = "ライセンス工程パターンマスタID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス区分マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "ライセンス区分マスタID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;

	/**
	 * 工程パターンID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "工程パターンID", required = true, allowableValues = "range[0,9223372036854775807]")
	private long processPatternId;

	/**
	 * 工程順
	 */
	@Column(nullable = false)
	@Schema(description = "工程順", required = true, allowableValues = "range[0,999]")
	private int processOrder;

	/**
	 * ライセンス工程マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "process_master_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "ライセンス工程マスタ", required = true)
	private LicenseProcessMaster licenseProcessMaster;

	/**
	 * 手配業務タイプマスタID
	 */
	@Min(0)
	@Schema(description = "手配業務タイプマスタID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long arrangementWorkTypeMasterId;

	/**
	 * メール到達チェックフラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "メール到達チェックフラグ", required = false, allowableValues = "range[0,9]")
	private Integer mailArrivalCheckFlg;

	/**
	 * メール区分
	 */
	@Schema(description = "メール区分", required = true, allowableValues = "事前設定完了メール(\"1\"), Welcomeメール(\"2\")")
	private MailDiv mailDiv;

	/**
	 * メールマスタID
	 */
	@Min(0)
	@Schema(description = "メールマスタID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long mailMasterId;

	/**
	 * メール到達チェック時間
	 */
	@Max(99999)
	@Min(0)
	@Schema(description = "メール到達チェック時間", required = false, allowableValues = "range[0,99999]")
	private Integer mailArrivalCheckHour;

	/**
	 * 到着チェックメール制御マスタID
	 */
	@Min(0)
	@Schema(description = "到着チェックメール制御マスタID", required = false, allowableValues = "range[0,9223372036854775807]")
	private Long arrivalCheckMailControlMasterId;
}
