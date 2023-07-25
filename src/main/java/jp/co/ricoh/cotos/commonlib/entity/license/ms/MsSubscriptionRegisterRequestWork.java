package jp.co.ricoh.cotos.commonlib.entity.license.ms;

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
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MS_サブスクリプション登録リクエストWORK
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "ms_subscription_register_request_work")
public class MsSubscriptionRegisterRequestWork extends AbstractMsRequestWork {
	@Description(value = "増減区分")
	public enum IncreaseDecreaseDiv {

		増数("1"), 減数("2"), アップグレード("3"), 更新期限の更新("4");

		private final String text;

		private IncreaseDecreaseDiv(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static IncreaseDecreaseDiv fromString(String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ms_subscription_register_request_work_seq")
	@SequenceGenerator(name = "ms_subscription_register_request_work_seq", sequenceName = "ms_subscription_register_request_work_seq", allocationSize = 1)
	@ApiModelProperty(value = "MS_サブスクリプション登録リクエストWORK", required = true, position = 1, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * ライセンス情報ID
	 */
	@Column(nullable = false)
	@ApiModelProperty(value = "ライセンス情報ID", required = true, position = 2, allowableValues = "range[0,9223372036854775807]")
	private Long licenseInfoId;

	/**
	 * 処理状態
	 */
	@ApiModelProperty(value = "処理状態", required = false, position = 3, allowableValues = "未処理(\"0\"), 出力済(\"1\"), 連携対象外(\"2\")")
	private MsRequestStatus processStatus;

	/**
	 * 顧客テナントID
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "顧客テナントID", required = true, position = 4, allowableValues = "range[0,255]")
	private String customerId;

	/**
	 * 製品ID
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "製品ID", required = true, position = 5, allowableValues = "range[0,255]")
	private String offerId;

	/**
	 * 数量
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "数量", required = false, position = 6, allowableValues = "range[0,255]")
	private String quantity;

	/**
	 * サブスクリプションID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サブスクリプションID", required = false, position = 7, allowableValues = "range[0,255]")
	private String subscriptionId;

	/**
	 * ライセンスNO
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@ApiModelProperty(value = "ライセンスNO", required = true, position = 8, allowableValues = "range[0,255]")
	private String licenseNo;

	/**
	 * プロダクト名
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "プロダクト名", required = false, position = 9, allowableValues = "range[0,255]")
	private String productName;

	/**
	 * 契約期間
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "契約期間", required = false, position = 10, allowableValues = "range[0,255]")
	private String contractTerm;

	/**
	 * 増減区分
	 */
	@ApiModelProperty(value = "増減区分", required = false, position = 11, allowableValues = "増数(\"1\"), 減数(\"2\")")
	private IncreaseDecreaseDiv increaseDecreaseDiv;

	/**
	 * 移行元サブスクリプションID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "移行元サブスクリプションID", required = false, position = 12, allowableValues = "range[0,255]")
	private String fromSubscriptionId;

	/**
	 * 移行元製品ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "移行元製品ID", required = false, position = 13, allowableValues = "range[0,255]")
	private String fromOfferId;

	/**
	 * 処理優先順
	 */
	@Max(999)
	@Min(0)
	@ApiModelProperty(value = "処理優先順", required = false, position = 14, allowableValues = "range[0,999]")
	private Integer priorityOrder;

	/**
	 * 調整後の終了日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "調整後の終了日", required = false, position = 15)
	private Date customTermEndDate;
}
