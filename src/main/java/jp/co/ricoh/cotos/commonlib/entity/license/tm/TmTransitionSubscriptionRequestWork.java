package jp.co.ricoh.cotos.commonlib.entity.license.tm;

import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロサブスクリプション乗換リクエストWORK
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tm_transition_subscription_request_work")
public class TmTransitionSubscriptionRequestWork extends AbstractTmRequestWork {

	@Description(value = "クロスグレード区分")
	public enum CrossGradeDiv {

		アップグレード("1"), ダウングレード("2");

		private final String text;

		private CrossGradeDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static CrossGradeDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tm_transition_subscription_request_work_seq")
	@SequenceGenerator(name = "tm_transition_subscription_request_work_seq", sequenceName = "tm_transition_subscription_request_work_seq", allocationSize = 1)
	@Schema(description = "サブスクリプション乗換リクエストWORK ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 会社ID
	 */
	@Size(max = 255)
	@Schema(description = "会社ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String customerId;

	/**
	 * サービスプランID
	 */
	@Size(max = 255)
	@Schema(description = "サービスプランID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String servicePlanId;

	/**
	 * ユニット数
	 */
	@Size(max = 255)
	@Schema(description = "ユニット数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String unitsPerLicense;

	/**
	 * 乗換ユニット数
	 */
	@Size(max = 255)
	@Schema(description = "乗換ユニット数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String transitionUnitsPerLicense;

	/**
	 * ライセンス開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ライセンス開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date licenseStartDate;

	/**
	 * クロスグレード区分
	 */
	@Schema(description = "クロスグレード区分", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "アップグレード(\"1\"), ダウングレード(\"2\")", example = "1")
	private CrossGradeDiv crossGradeDiv;

	/**
	 * 乗換元サービスプランID
	 */
	@Size(max = 255)
	@Schema(description = "乗換元サービスプランID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String fromServicePlanId;

	/**
	 * 乗換元ライセンス終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "乗換元ライセンス終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Date fromLicenseEndDate;

	/**
	 * 乗換元解約済フラグ
	 */
	@Max(9)
	@Min(0)
	@Schema(description = "乗換元解約済フラグ", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer fromSuspendFlg;

	/**
	 * 乗換元サブスクリプションID
	 */
	@Size(max = 255)
	@Schema(description = "乗換元サブスクリプションID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String fromSubscriptionId;

	/**
	 * トレンドマイクロサブスクリプション乗換レスポンスWORK
	 */
	@Valid
	@OneToOne(mappedBy = "requestWork")
	@Schema(description = "トレンドマイクロサブスクリプション乗換レスポンスWORK", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private TmTransitionSubscriptionResponseWork responseWork;
}
