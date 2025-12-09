package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import java.util.Date;

import jakarta.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロサブスクリプション更新レスポンスDTO
 */
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@Data
public class TmUpdateSubscriptionResponseDto extends AbstractTmResponseDto {

	/**
	 * サブスクリプションID
	 */
	@JsonProperty("subscription_id")
	private String subscriptionId;

	/**
	 * サービスプラン名称
	 */

	private String name;

	/**
	 * サブスクリプションステータス
	 */
	private Boolean enabled;

	/**
	 * 自動更新有効無効設定
	 */
	@JsonProperty("is_auto_renewal")
	private Boolean isAutoRenewal;

	/**
	 * 月額更新月数
	 */
	@JsonProperty("auto_renewal_month")
	private String autoRenewalMonth;

	/**
	 * 有効期限通知日数
	 */
	@JsonProperty("expiration_notification")
	private String expirationNotification;

	/**
	 * ログイン画面URL
	 */
	@JsonProperty("service_url")
	private String serviceUrl;

	private TmUpdateSubscriptionResponseDtoLisense[] licenses;

	@Data
	public static class TmUpdateSubscriptionResponseDtoLisense {

		/**
		 * アクティベーションコード
		 */
		@JsonProperty("ac_code")
		private String acCode;

		/**
		 * 製品ID
		 */
		@JsonProperty("product_id")
		private String productId;

		/**
		 * ライセンス開始日
		 */
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "Asia/Tokyo")
		@JsonProperty("license_start_date")
		private Date licenseStartDate;

		/**
		 * ライセンス終了日
		 */
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "Asia/Tokyo")
		@JsonProperty("license_expiration_date")
		private Date licenseExpirationDate;

		/**
		 * 課金開始日
		 */
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "Asia/Tokyo")
		@JsonProperty("start_charge_date")
		private Date startChargeDate;

		/**
		 * 猶予期間
		 */
		@Schema(description = "猶予期間", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
		@JsonProperty("grace_period")
		private Integer gracePeriod;

		/**
		 * シート数
		 */
		@Schema(description = "シート数", requiredMode = Schema.RequiredMode.NOT_REQUIRED, allowableValues = "range[0,9]")
		@JsonProperty("units")
		private Integer units;

		/**
		 * ライセンスステータス
		 */
		@Schema(description = "ライセンスステータス", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
		@JsonProperty("enabled")
		private Boolean licenseEnabled;
	}

}
