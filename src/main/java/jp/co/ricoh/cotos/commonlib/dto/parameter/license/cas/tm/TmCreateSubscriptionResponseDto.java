package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import java.util.Date;

import jakarta.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロサブスクリプション作成レスポンスDTO
 */
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@Data
public class TmCreateSubscriptionResponseDto extends AbstractTmResponseDto {

	/**
	 * サブスクリプションID
	 */
	@JsonProperty("subscription_id")
	private String subscriptionId;
	/**
	 * 製品名
	 */
	@JsonProperty("product_name")
	private String productName;

	/**
	* ログイン画面URL
	*/
	@JsonProperty("service_url")
	private String serviceUrl;

	private TmCreateSubscriptionResponseDtoLicense[] licenses;

	@Data
	public static class TmCreateSubscriptionResponseDtoLicense {

		/**
		 * 製品ID
		 */
		@JsonProperty("product_id")
		private String productId;

		/**
		 * 製品版/体験版
		 */
		@JsonProperty("version")
		private String versionlicenceVersion;

		/**
		 * アクティベーションコード
		 */
		@JsonProperty("ac_code")
		private String acCode;

		/**
		 * シート数
		 */
		private String units;

		/**
		 * ライセンス開始日
		 */
		@JsonProperty("license_start_date")
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "Asia/Tokyo")
		private Date licenseStartDate;

		/**
		 * ライセンス終了日
		 */
		@JsonProperty("license_expiration_date")
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "Asia/Tokyo")
		private Date licenseExpirationDate;

		/**
		 * 課金開始日
		 */
		@JsonProperty("start_charge_date")
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "Asia/Tokyo")
		private Date startChargeDate;
	}
}
