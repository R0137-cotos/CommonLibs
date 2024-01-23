package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserCredentialsDto {

	/*
	 * the name of the user.
	 */
	private String userName;

	/*
	 * the password.
	 */
	private String password;

}
