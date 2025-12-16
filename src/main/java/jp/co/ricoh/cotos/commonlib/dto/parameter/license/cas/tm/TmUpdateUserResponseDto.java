package jp.co.ricoh.cotos.commonlib.dto.parameter.license.cas.tm;

import jakarta.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ユーザーアカウント更新レスポンスWORK
 */
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@Data
public class TmUpdateUserResponseDto extends AbstractTmResponseDto {

	public TmUpdateUserResponseDto() {
		this.phone = new TmUpdateUserResponseDtoPhone();
	}

	/**
	 * ユーザーID
	 */
	@JsonProperty("user_id")
	private String userId;

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

	private TmUpdateUserResponseDtoPhone phone;

	@Data
	public class TmUpdateUserResponseDtoPhone {

		/**
		 * 市外局番
		 */
		@JsonProperty("area_code")
		private String areaCode;

		/**
		 * 電話番号
		 */
		private String number;

		/**
		 * 内線番号
		 */
		private String extension;
	}
}
