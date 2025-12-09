package jp.co.ricoh.cotos.commonlib.entity.license.ms;

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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import org.springframework.context.annotation.Description;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;
import jp.co.ricoh.cotos.commonlib.entity.EnumType.PaymentCycleType;
import jp.co.ricoh.cotos.commonlib.entity.master.LicenseServiceMaster.LicenseType;
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

		増数("1"), 減数("2"), アップグレード("3"), 契約期間の変更("4"),支払周期変更("5");

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

	@Description(value = "サブスクリプション連携状態")
	public enum MsSubscriptionLinkageStatus {
		未処理("0"), 連携済("1"), 反映確認済("2"), 処理対象外("3"), エラー("4");

		private final String text;

		private MsSubscriptionLinkageStatus(final String text) {
			this.text = text;
		}

		@Override
		@JsonValue
		public String toString() {
			return this.text;
		}

		@JsonCreator
		public static MsSubscriptionLinkageStatus fromString(final String string) {
			return Arrays.stream(values()).filter(v -> v.text.equals(string)).findFirst().orElseThrow(() -> new IllegalArgumentException(String.valueOf(string)));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ms_subscription_register_request_work_seq")
	@SequenceGenerator(name = "ms_subscription_register_request_work_seq", sequenceName = "ms_subscription_register_request_work_seq", allocationSize = 1)
	@Schema(description = "MS_サブスクリプション登録リクエストWORK", required = true, allowableValues = "range[0,9223372036854775807]")
	private long id;

	/**
	 * ライセンス情報ID
	 */
	@Column(nullable = false)
	@Schema(description = "ライセンス情報ID", required = true, allowableValues = "range[0,9223372036854775807]")
	private Long licenseInfoId;

	/**
	 * 処理状態
	 */
	@Schema(description = "処理状態", required = false, allowableValues = "未処理(\"0\"), 出力済(\"1\"), 連携対象外(\"2\")")
	private MsRequestStatus processStatus;

	/**
	 * 顧客テナントID
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "顧客テナントID", required = true, allowableValues = "range[0,255]")
	private String customerId;

	/**
	 * 製品ID
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "製品ID", required = true, allowableValues = "range[0,255]")
	private String offerId;

	/**
	 * 数量
	 */
	@Size(max = 255)
	@Schema(description = "数量", required = false, allowableValues = "range[0,255]")
	private String quantity;

	/**
	 * サブスクリプションID
	 */
	@Size(max = 255)
	@Schema(description = "サブスクリプションID", required = false, allowableValues = "range[0,255]")
	private String subscriptionId;

	/**
	 * ライセンスNO
	 */
	@Column(nullable = false)
	@Size(max = 255)
	@Schema(description = "ライセンスNO", required = true, allowableValues = "range[0,255]")
	private String licenseNo;

	/**
	 * プロダクト名
	 */
	@Size(max = 255)
	@Schema(description = "プロダクト名", required = false, allowableValues = "range[0,255]")
	private String productName;

	/**
	 * 契約期間
	 */
	@Size(max = 255)
	@Schema(description = "契約期間", required = false, allowableValues = "range[0,255]")
	private String contractTerm;

	/**
	 * 増減区分
	 */
	@Schema(description = "増減区分", required = false, allowableValues = "増数(\"1\"), 減数(\"2\")")
	private IncreaseDecreaseDiv increaseDecreaseDiv;

	/**
	 * 移行元サブスクリプションID
	 */
	@Size(max = 255)
	@Schema(description = "移行元サブスクリプションID", required = false, allowableValues = "range[0,255]")
	private String fromSubscriptionId;

	/**
	 * 移行元製品ID
	 */
	@Size(max = 255)
	@Schema(description = "移行元製品ID", required = false, allowableValues = "range[0,255]")
	private String fromOfferId;

	/**
	 * 処理優先順
	 */
	@Max(999)
	@Min(0)
	@Schema(description = "処理優先順", required = false, allowableValues = "range[0,999]")
	private Integer priorityOrder;

	/**
	 * 調整後の終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "調整後の終了日", required = false)
	private Date customTermEndDate;

	/**
	 * サブスクリプション連携状態
	 */
	@Schema(description = "サブスクリプション連携状態", required = false, allowableValues = "未処理(\"0\"), 連携済(\"1\"), 反映確認済(\"2\"), 処理対象外(\"3\"), エラー(\"4\")")
	private MsSubscriptionLinkageStatus subscriptionLinkageStatus;

	/**
	 * 再実行日時
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Schema(description = "再実行日時", required = false)
	private Date retryDate;

	/**
	 * 連携済サブスクリプションID
	 */
	@Size(max = 255)
	@Schema(description = "連携済サブスクリプションID", required = false, allowableValues = "range[0,255]")
	private String linkedSubscriptionId;

	/**
	 * 支払周期区分
	 */
	@Schema(description = "支払周期区分", required = false, allowableValues = "月額(\"1\"), 年額(\"2\")")
	private PaymentCycleType paymentCycleType;

	/**
	 * ライセンス区分
	 */
	@Schema(description = "ライセンス区分", required = false, allowableValues = "ベース(\"1\"), アドオン(\"2\")")
	private LicenseType licenseType;
}
