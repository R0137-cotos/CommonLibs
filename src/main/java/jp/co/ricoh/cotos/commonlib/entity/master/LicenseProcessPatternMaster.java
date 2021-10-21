package jp.co.ricoh.cotos.commonlib.entity.master;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
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
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst()
					.orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * ライセンス工程パターンマスタID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_process_pattern_master_seq")
	@SequenceGenerator(name = "license_process_pattern_master_seq", sequenceName = "license_process_pattern_master_seq", allocationSize = 1)
	@ApiModelProperty(value = "ライセンス工程パターンマスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス区分マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "ライセンス区分マスタID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private long licenseDivMasterId;

	/**
	 * 工程パターンID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "工程パターンID", required = true, position = 3, allowableValues = "range[0,9223372036854775807]")
	private long processPatternId;

	/**
	 * 工程順
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "工程順", required = true, position = 4, allowableValues = "range[0,999]")
	private int processOrder;

	/**
	 * ライセンス工程マスタ
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "process_master_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "ライセンス工程マスタ", required = true, position = 5)
	private LicenseProcessMaster licenseProcessMaster;

	/**
	 * 手配業務タイプマスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "手配業務タイプマスタID", required = false, position = 6, allowableValues = "range[0,9223372036854775807]")
	private Long arrangementWorkTypeMasterId;

	/**
	 * メール到達チェックフラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "メール到達チェックフラグ", required = false, position = 7, allowableValues = "range[0,9]")
	private Integer mailArrivalCheckFlg;

	/**
	 * メール区分
	 */
	@ApiModelProperty(value = "メール区分", required = true, position = 8, allowableValues = "事前設定完了メール(\"1\"), Welcomeメール(\"2\")")
	private MailDiv mailDiv;

	/**
	 * メールマスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "メールマスタID", required = false, position = 9, allowableValues = "range[0,9223372036854775807]")
	private Long mailMasterId;
}
