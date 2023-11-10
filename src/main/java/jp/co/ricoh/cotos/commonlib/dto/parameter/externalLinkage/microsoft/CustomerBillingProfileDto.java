package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CustomerBillingProfileDto extends ResorceBaseWithLinks {

	/*
	 * the profile identifier.
	 */
	private String id;

	/*
	 * the first name.
	 */
	private String firstName;

	/*
	 * the last name.
	 */
	private String lastName;

	/*
	 * the email.
	 */
	private String email;

	/*
	 * the culture.
	 */
	private String culture;

	/*
	 * the language.
	 */
	private String language;

	/*
	 * the name of the company.
	 */
	private String companyName;

	/*
	 * the default address for customer.
	 */
	private DefaultAddressDto defaultAddress;

}
