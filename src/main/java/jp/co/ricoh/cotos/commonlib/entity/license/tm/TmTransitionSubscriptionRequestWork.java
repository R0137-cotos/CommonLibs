package jp.co.ricoh.cotos.commonlib.entity.license.tm;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "サブスクリプション乗換リクエストWORK", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * 会社ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "会社ID", required = false, position = 7, allowableValues = "range[0,255]")
	private String customerId;

	/**
	 * サービスプランID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サービスプランID", required = false, position = 8, allowableValues = "range[0,255]")
	private String servicePlanId;

	/**
	 * ユニット数
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ユニット数", required = false, position = 9, allowableValues = "range[0,255]")
	private String unitsPerLicense;

	/**
	 * 乗換ユニット数
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ユニット数", required = false, position = 10, allowableValues = "range[0,255]")
	private String transitionUnitsPerLicense;

	/**
	 * ライセンス開始日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ライセンス開始日", required = false, position = 11)
	private Date licenseStartDate;

	/**
	 * クロスグレード区分
	 */
	@ApiModelProperty(value = "クロスグレード区分", required = false, allowableValues = "アップグレード(\"1\"), ダウングレード(\"2\")", example = "1", position = 12)
	private CrossGradeDiv crossGradeDiv;

	/**
	 * 乗換元サービスプランID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "乗換元サービスプランID", required = false, position = 13, allowableValues = "range[0,255]")
	private String fromServicePlanId;

	/**
	 * 乗換元ライセンス終了日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "乗換元ライセンス終了日", required = false, position = 14)
	private Date fromLicenseEndDate;

	/**
	 * 乗換元解約済フラグ
	 */
	@Max(9)
	@Min(0)
	@ApiModelProperty(value = "乗換元解約済フラグ", required = false, position = 15, allowableValues = "range[0,9]")
	private Integer fromSuspendFlg;

	/**
	 * トレンドマイクロサブスクリプション乗換レスポンスWORK
	 */
	@Valid
	@OneToOne(mappedBy = "requestWork")
	@ApiModelProperty(value = "トレンドマイクロサブスクリプション作成レスポンスWORK", required = false, position = 16)
	private TmTransitionSubscriptionResponseWork responseWork;
}
