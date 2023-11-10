package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DefaultAddressDto {

	/*
	 * the Country in ISO country code format.
	 */
	private String country;

	/*
	 * the region.
	 */
	private String region;

	/*
	 * the city.
	 */
	private String city;

	/*
	 * the state.
	 */
	private String state;

	/*
	 * the first address line.
	 */
	private String addressLine1;

	/*
	 * the second address line.
	 */
	private String addressLine2;

	/*
	 * the postal code.
	 */
	private String postalCode;

	/*
	 * the first name.
	 */
	private String firstName;

	/*
	 * the middle name.
	 */
	private String middleName;

	/*
	 * the last name.
	 */
	private String lastName;

	/*
	 * the phone.
	 */
	private String phoneNumber;

}
