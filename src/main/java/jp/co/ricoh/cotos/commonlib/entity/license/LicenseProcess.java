package jp.co.ricoh.cotos.commonlib.entity.license;

import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
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
	@Schema(description = "ライセンス工程ID(作成時不要)", required = true, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * ライセンス情報
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "license_info_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "ライセンス情報", requiredMode = Schema.RequiredMode.REQUIRED)
	private LicenseInfo licenseInfo;

	/**
	 * 工程順
	 */
	@Column(nullable = false)
	@Max(999)
	@Min(0)
	@Schema(description = "工程順", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,999]")
	private int processOrder;

	/**
	 * ライセンス工程マスタID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "ライセンス工程マスタID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long processMasterId;

	/**
	 * 手配業務ID
	 */
	@Column(nullable = false)
	@Min(0)
	@Schema(description = "手配業務ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private long arrangementWorkId;

	/**
	 * 操作区分
	 */
	@NotNull
	@Column(nullable = false)
	@Schema(description = "操作区分", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "受付(\"1\"), ボタン(\"2\"), CSV出力(\"3\"), CSV取込(\"4\")")
	private OperationDiv operationDiv;

	/**
	 * メール到達チェックフラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "メール到達チェックフラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer mailArrivalCheckFlg;

	/**
	 * メール送信日
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "メール送信日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date mailSendAt;

	/**
	 * 送信結果区分
	 */
	@Schema(description = "送信結果区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未送信(\"0\"), 送信中(\"1\"), 送信済(\"2\"), 不達(\"3\"), 送信エラー(\"4\")")
	private MailSendResultDiv mailSendResultDiv;

	/**
	 * メール区分
	 */
	@Schema(description = "メール区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "事前設定完了メール(\"1\"), Welcomeメール(\"2\")")
	private MailDiv mailDiv;

	/**
	 * 工程状態
	 */
	@Schema(description = "工程状態", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "未処理(\"0\"), 完了(\"1\"), 破棄(\"2\")")
	private ProcessStatus processStatus;

	/**
	 * メールマスタID
	 */
	@Min(0)
	@Schema(description = "メールマスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long mailMasterId;

	/**
	 * メール到着チェック時間
	 */
	@Max(99999)
	@Min(-99999)
	@Schema(description = "メール到着チェック時間", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[-99999,99999]")
	private Integer mailArrivalCheckHour;

	/**
	 * 到着チェックメール制御マスタID
	 */
	@Min(0)
	@Schema(description = "到着チェックメール制御マスタID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9223372036854775807]")
	private Long arrivalCheckMailControlMasterId;

	/**
	 * 実施日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "実施日時", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date operatedAt;
}
