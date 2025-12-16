package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import jakarta.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロ会社情報更新レスポンスDTO
 */
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@Data
public class TmUpdateCustomerResponseDto extends AbstractTmResponseDto {

	/**
	 * カスタマーID
	 */
	@JsonProperty("customer_id")
	private String customerId;

	/**
	 * 会社
	 */
	private String name;

	/**
	 * 都道府県
	 */
	private String state;

	/**
	 * 国
	 */
	private String country;

	/**
	 * 市区町村
	 */
	private String city;

	/**
	 * 住所
	 */
	private String address;

	/**
	 * 郵便番号
	 */
	@JsonProperty("postal_code")
	private String postalCode;

	/**
	 * 備考
	 */
	private String note;

	/**
	 * その他のメールアドレス
	 */
	@JsonProperty("emergency_email")
	private String emergencyEmail;

}
