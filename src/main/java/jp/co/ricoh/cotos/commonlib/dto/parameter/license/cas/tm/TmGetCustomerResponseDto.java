package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import jakarta.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 更新ユーザー取得レスポンスDTO
 */
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@Data
public class TmGetCustomerResponseDto extends AbstractTmResponseDto {

	@Data
	public static class TmGetCustomerResponseDtoPaging {

		private int total;

		private int limit;

		private int page;
	}

	private TmGetCustomerResponseDtoPaging paging;

	@Data
	public static class TmGetCustomerResponseDtoUser {

		private int status;

		@JsonProperty("first_name")
		private String firstName;

		@JsonProperty("last_name")
		private String lastName;

		@JsonProperty("user_id")
		private String userId;

		private String note;

		private String language;

		@JsonProperty("time_zone")
		private String timeZone;

		private String email;

		@JsonProperty("customer_id")
		private String customerId;

		@JsonProperty("login_name")
		private String loginName;

		@JsonProperty("emergency_email")
		private String emergencyEmail;

		private TmGetCustomerResponseDtoPhone phone;
	}

	@Data
	public static class TmGetCustomerResponseDtoPhone {

		@JsonProperty("area_code")
		private String areaCode;

		private String number;

		private String extension;
	}

	private TmGetCustomerResponseDtoUser[] users;

}
