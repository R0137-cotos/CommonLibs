package jp.co.ricoh.cotos.commonlib.entity.license;

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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.master.LicenseProcessMaster.OperationDiv;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ライセンス工程を表すEntity
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "license_process")
public class LicenseProcess extends EntityBase {

	public enum MailSendedResultDiv {

		未送信("0"), 送信中("1"), 送信済("2"), 不達("3"), 送信エラー("4");

		private final String text;

		private MailSendedResultDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static MailSendedResultDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	/**
	 * ライセンス工程ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "license_process_seq")
	@SequenceGenerator(name = "license_process_seq", sequenceName = "license_process_seq", allocationSize = 1)
	@ApiModelProperty(value = "ライセンス工程ID(作成時不要)", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス情報ID
	 */
	@NotNull
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "ライセンス情報ID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private Long licenseInfoId;

	/**
	 * 工程順
	 */
	@NotNull
	@Column(nullable = false)
	@ApiModelProperty(value = "工程順", required = true, position = 3, allowableValues = "range[0,999]")
	private int processOrder;

	/**
	 * 工程ID
	 *
	 */
	@NotNull
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "工程ID", required = true, position = 4, allowableValues = "range[0,9223372036854775807]")
	private Long processId;

	/**
	 * 手配業務ID
	 */
	@NotNull
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "手配業務ID", required = true, position = 5, allowableValues = "range[0,9223372036854775807]")
	private Long arrangementWorkId;

	/**
	 * 操作区分
	 */
	@NotNull
	@Column(nullable = false)
	@ApiModelProperty(value = "操作区分", required = true, allowableValues = "受付(\"1\"), ボタン(\"2\"), CSV出力(\"3\"), CSV取込(\"4\")", position = 6)
	private OperationDiv operationDiv;

	/**
	 * 通知メール制御マスタID
	 *
	 */
	@Min(0)
	@ApiModelProperty(value = "通知メール制御マスタID", required = false, position = 7, allowableValues = "range[0,9223372036854775807]")
	private Long mailControlMasterId;

	/**
	 * メール到達チェックフラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "メール到達チェックフラグ", required = false, position = 8, allowableValues = "range[0,9]")
	private Integer mailArrivalCheckId;

	/**
	 * メール送信日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "メール送信日", required = false, position = 9)
	private Date mailSendedAt;

	/**
	 * 送信結果区分
	 */
	@ApiModelProperty(value = "送信結果区分", required = false, allowableValues = "未送信(\"0\"), 送信中(\"1\"), 送信済(\"2\"), 不達(\"3\"), 送信エラー(\"4\")", position = 10)
	private MailSendedResultDiv mailSendedResultDiv;

	/**
	 * 完了フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "完了フラグ", required = false, position = 11, allowableValues = "range[0,9]")
	private Integer completeFlg;

}
