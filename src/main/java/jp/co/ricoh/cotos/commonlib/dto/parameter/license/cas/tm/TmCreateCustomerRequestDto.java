package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * トレンドマイクロ顧客情報作成リクエストDTO
 */
@Data
public class TmCreateCustomerRequestDto implements AbstractTmRequestDto {

	public TmCreateCustomerRequestDto() {
		this.company = new TmCreateCustomerRequestDtoCompany();
		this.user = new TmCreateCustomerRequestDtoUser();
	}

	private TmCreateCustomerRequestDtoCompany company;

	@Data
	public class TmCreateCustomerRequestDtoCompany {

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
	}

	private TmCreateCustomerRequestDtoUser user;

	@Data
	public class TmCreateCustomerRequestDtoUser {

		/**
		 * アカウント
		 */
		@JsonProperty("login_name")
		private String loginName;

		/**
		 * 担当者名
		 */
		@JsonProperty("first_name")
		private String firstName;

		/**
		 * 担当者姓
		 */
		@JsonProperty("last_name")
		private String lastName;

		/**
		 * メールアドレス
		 */
		private String email;

		/**
		 * タイムゾーン
		 */
		@JsonProperty("time_zone")
		private String timeZone;

		/**
		 * 言語
		 */
		private String language;
	}

}
