package jp.co.ricoh.cotos.commonlib.entity.license.tm;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロサブスクリプション更新レスポンスWORK
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "tm_update_subscription_response_work")
public class TmUpdateSubscriptionResponseWork extends AbstractTmResponseWork {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tm_update_subscription_response_work_seq")
	@SequenceGenerator(name = "tm_update_subscription_response_work_seq", sequenceName = "tm_update_subscription_response_work_seq", allocationSize = 1)
	@Schema(description = "トレンドマイクロサブスクリプション更新レスポンスWORK ID", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * トレンドマイクロサブスクリプション更新リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "request_id", referencedColumnName = "id")
	@JsonIgnore
	@Schema(description = "トレンドマイクロサブスクリプション更新リクエストWORK", requiredMode = Schema.RequiredMode.REQUIRED)
	private TmUpdateSubscriptionRequestWork requestWork;

	/**
	 * サブスクリプションID
	 */
	@Size(max = 255)
	@Schema(description = "サブスクリプションID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String subscriptionId;

	/**
	 * サービスプラン名称
	 */
	@Size(max = 255)
	@Schema(description = "サービスプラン名称", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String name;

	/**
	 * サブスクリプションステータス
	 */
	@Schema(description = "サブスクリプションステータス", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Boolean enabled;

	/**
	 * 自動更新有効無効設定
	 */
	@Schema(description = "自動更新有効無効設定", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Boolean isAutoRenewal;

	/**
	 * 月額更新月数
	 */
	@Size(max = 255)
	@Schema(description = "月額更新月数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String autoRenewalMonth;

	/**
	 * 有効期限通知日数
	 */
	@Size(max = 255)
	@Schema(description = "有効期限通知日数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String expirationNotification;

	/**
	 * ログイン画面URL
	 */
	@Size(max = 255)
	@Schema(description = "ログイン画面URL", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String serviceUrl;

	/**
	 * アクティベーションコード
	 */
	@Size(max = 255)
	@Schema(description = "アクティベーションコード", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String acCode;

	/**
	 * 製品ID
	 */
	@Size(max = 255)
	@Schema(description = "製品ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private String productId;

	/**
	 * ライセンス開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ライセンス開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private Date licenseStartDate;

	/**
	 * ライセンス終了日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "ライセンス終了日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private Date licenseExpirationDate;

	/**
	 * 課金開始日
	 */
	@Temporal(TemporalType.DATE)
	@Schema(description = "課金開始日", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,255]")
	private Date startChargeDate;

	/**
	 * 猶予期間
	 */
	@Schema(description = "猶予期間", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer gracePeriod;

	/**
	 * シート数
	 */
	@Schema(description = "シート数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
	private Integer units;

	/**
	 * ライセンスステータス
	 */
	@Schema(description = "ライセンスステータス", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private Boolean licenseEnabled;

}
