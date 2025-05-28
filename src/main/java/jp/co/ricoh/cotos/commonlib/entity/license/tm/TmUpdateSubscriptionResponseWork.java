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

import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(value = "トレンドマイクロサブスクリプション更新レスポンスWORK ID", required = true, position = 1, allowableValues = "range[0,9223372036854775807]", readOnly = true)
	private long id;

	/**
	 * トレンドマイクロサブスクリプション更新リクエストWORK
	 */
	@OneToOne
	@JoinColumn(name = "request_id", referencedColumnName = "id")
	@JsonIgnore
	@ApiModelProperty(value = "トレンドマイクロサブスクリプション更新リクエストWORK", required = true, position = 2)
	private TmUpdateSubscriptionRequestWork requestWork;

	/**
	 * サブスクリプションID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サブスクリプションID", required = false, position = 6, allowableValues = "range[0,255]")
	private String subscriptionId;

	/**
	 * サービスプラン名称
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "サービスプラン名称", required = false, position = 7, allowableValues = "range[0,255]")
	private String name;

	/**
	 * サブスクリプションステータス
	 */
	@ApiModelProperty(value = "サブスクリプションステータス", required = false, position = 8)
	private Boolean enabled;

	/**
	 * 自動更新有効無効設定
	 */
	@ApiModelProperty(value = "自動更新有効無効設定", required = false, position = 9)
	private Boolean isAutoRenewal;

	/**
	 * 月額更新月数
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "月額更新月数", required = false, position = 10, allowableValues = "range[0,255]")
	private String autoRenewalMonth;

	/**
	 * 有効期限通知日数
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "有効期限通知日数", required = false, position = 11, allowableValues = "range[0,255]")
	private String expirationNotification;

	/**
	 * ログイン画面URL
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "ログイン画面URL", required = false, position = 12, allowableValues = "range[0,255]")
	private String serviceUrl;

	/**
	 * アクティベーションコード
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "アクティベーションコード", required = false, position = 13, allowableValues = "range[0,255]")
	private String acCode;

	/**
	 * 製品ID
	 */
	@Size(max = 255)
	@ApiModelProperty(value = "製品ID", required = false, position = 14, allowableValues = "range[0,255]")
	private String productId;

	/**
	 * ライセンス開始日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ライセンス開始日", required = false, position = 15, allowableValues = "range[0,255]")
	private Date licenseStartDate;

	/**
	 * ライセンス終了日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "ライセンス終了日", required = false, position = 16, allowableValues = "range[0,255]")
	private Date licenseExpirationDate;

	/**
	 * 課金開始日
	 */
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(value = "課金開始日", required = false, position = 17, allowableValues = "range[0,255]")
	private Date startChargeDate;

	/**
	 * 猶予期間
	 */
	@ApiModelProperty(value = "猶予期間", required = false, position = 18, allowableValues = "range[0,9]")
	private Integer gracePeriod;

	/**
	 * シート数
	 */
	@ApiModelProperty(value = "シート数", required = false, position = 19, allowableValues = "range[0,9]")
	private Integer units;

	/**
	 * ライセンスステータス
	 */
	@ApiModelProperty(value = "ライセンスステータス", required = false, position = 20)
	private Boolean licenseEnabled;

}
