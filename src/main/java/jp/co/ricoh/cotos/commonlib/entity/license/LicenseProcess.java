package jp.co.ricoh.cotos.commonlib.entity.license;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import jp.co.ricoh.cotos.commonlib.entity.EntityBase;
import jp.co.ricoh.cotos.commonlib.entity.master.LicenseProcessMaster.OperationDiv;
import jp.co.ricoh.cotos.commonlib.entity.master.LicenseProcessPatternMaster.MailDiv;
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

	@Description(value = "送信結果区分")
	public enum MailSendResultDiv {

		未送信("0"), 送信中("1"), 送信済("2"), 不達("3"), 送信エラー("4");

		private final String text;

		private MailSendResultDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static MailSendResultDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Description(value = "工程状態")
	public enum ProcessStatus {

		未処理("0"), 完了("1"), 破棄("2");

		private final String text;

		private ProcessStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static ProcessStatus fromString(String string) {
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
	 * ライセンス情報
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "license_info_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "ライセンス情報", required = true, position = 2)
	private LicenseInfo licenseInfo;

	/**
	 * 工程順
	 */
	@Column(nullable = false)
	@Max(999)
	@Min(0)
	@ApiModelProperty(value = "工程順", required = true, position = 3, allowableValues = "range[0,999]")
	private int processOrder;

	/**
	 * ライセンス工程マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "ライセンス工程マスタID", required = true, position = 4, allowableValues = "range[0,9223372036854775807]")
	private long processMasterId;

	/**
	 * 手配業務ID
	 */
	@Column(nullable = false)
	@Min(0)
	@ApiModelProperty(value = "手配業務ID", required = true, position = 5, allowableValues = "range[0,9223372036854775807]")
	private long arrangementWorkId;

	/**
	 * 操作区分
	 */
	@NotNull
	@Column(nullable = false)
	@ApiModelProperty(value = "操作区分", required = true, allowableValues = "受付(\"1\"), ボタン(\"2\"), CSV出力(\"3\"), CSV取込(\"4\")", position = 6)
	private OperationDiv operationDiv;

	/**
	 * メール到達チェックフラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "メール到達チェックフラグ", required = false, position = 7, allowableValues = "range[0,9]")
	private Integer mailArrivalCheckFlg;

	/**
	 * メール送信日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "メール送信日", required = false, position = 8)
	private Date mailSendAt;

	/**
	 * 送信結果区分
	 */
	@ApiModelProperty(value = "送信結果区分", required = false, position = 9, allowableValues = "未送信(\"0\"), 送信中(\"1\"), 送信済(\"2\"), 不達(\"3\"), 送信エラー(\"4\")")
	private MailSendResultDiv mailSendResultDiv;

	/**
	 * メール区分
	 */
	@ApiModelProperty(value = "メール区分", required = false, position = 10, allowableValues = "事前設定完了メール(\"1\"), Welcomeメール(\"2\")")
	private MailDiv mailDiv;

	/**
	 * 工程状態
	 */
	@ApiModelProperty(value = "工程状態", required = false, position = 11, allowableValues = "未処理(\"0\"), 完了(\"1\"), 破棄(\"2\")")
	private ProcessStatus processStatus;

	/**
	 * メールマスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "メールマスタID", required = false, position = 12, allowableValues = "range[0,9223372036854775807]")
	private Long mailMasterId;

	/**
	 * メール到着チェック時間
	 */
	@Max(99999)
	@Min(-99999)
	@ApiModelProperty(value = "メール到着チェック時間", required = false, position = 13, allowableValues = "range[-99999,99999]")
	private Integer mailArrivalCheckHour;

	/**
	 * 到着チェックメール制御マスタID
	 */
	@Min(0)
	@ApiModelProperty(value = "到着チェックメール制御マスタID", required = false, position = 14, allowableValues = "range[0,9223372036854775807]")
	private Long arrivalCheckMailControlMasterId;

	/**
	 * 実施日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "実施日時", required = false, position = 15)
	private Date operatedAt;
}
