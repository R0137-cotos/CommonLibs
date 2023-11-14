package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PrimaryContactDto {

	/*
	 * the contact's first name.
	 */
	private String firstName;

	/*
	 * the contact's last name.
	 */
	private String lastName;

	/*
	 * the contact's email address.
	 */
	private String email;

	/*
	 * the contact's phone number.
	 */
	private String phoneNumber;

}
