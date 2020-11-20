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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	public enum MailDiv {

		事前完了メール("1"), Welcameメール("2");

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
	@ApiModelProperty(value = "ライセンス工程パターンマスタID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス区分マスタID
	 */
	@NotNull
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "ライセンス区分マスタID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private Long licenseDivMasterId;

	/**
	 * 工程パターンID
	 */
	@NotNull
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "工程パターンID", required = true, position = 3, allowableValues = "range[0,9223372036854775807]")
	private Long processPatternId;

	/**
	 * 工程順
	 */
	@NotNull
	@Column(nullable = false)
	@ApiModelProperty(value = "工程順", required = true, position = 4, allowableValues = "range[0,999]")
	private int processOrder;

	/**
	 * ライセンス工程マスタ
	 */
	@NotNull
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
	 * メールテンプレートID
	 */
	@Min(0)
	@ApiModelProperty(value = "メールテンプレートID", required = false, position = 7, allowableValues = "range[0,9223372036854775807]")
	private Long mailTemplateId;

	/**
	 * メール到達チェックフラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "メール到達チェックフラグ", required = false, position = 8, allowableValues = "range[0,9]")
	private Integer mailArrivalCheckFlg;

	/**
	 * メール区分
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "メール区分", required = true, position = 9, allowableValues = "range[0,255]")
	private MailDiv mailDiv;

}
