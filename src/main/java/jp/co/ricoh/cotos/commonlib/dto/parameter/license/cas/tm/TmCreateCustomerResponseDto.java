package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import jakarta.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * トレンドマイクロ顧客情報作成レスポンスDTO
 */
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@Data
public class TmCreateCustomerResponseDto extends AbstractTmResponseDto {

	/**
	 * 会社ID
	 */
	@JsonProperty("customer_id")
	private String customerId;

	/**
	 * ユーザー
	 */
	private TmCreateCustomerResponseDtoUser user;

	@Data
	public static class TmCreateCustomerResponseDtoUser {

		/**
		 * ユーザーID
		 */
		private String id;

		/**
		 * ログインアカウント
		 */
		@JsonProperty("login_name")
		private String loginName;

		/**
		 * ログインパスワード
		 */
		private String password;

		/**
		 * リセット用パスワードURL
		 */
		private String resetpasswordURL;
	}
}
