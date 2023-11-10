package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CustomerDto extends ResorceBaseWithLinks {

	/*
	 * the customer active directory tenant ID.
	 */
	private String id;

	/*
	 * the customer commerce identifier.
	 */
	private String commerceId;

	/*
	 * the customer's company profile.
	 */
	private CustomerCompanyProfileDto companyProfile;

	/*
	 * the customer's billing profile.
	 */
	private CustomerBillingProfileDto billingProfile;

	/*
	 * the customer's relationship to partner.
	 */
	private String relationshipToPartner;

	/*
	 * that indicates if delegated access is allowed for this customer or not.
	 */
	private Boolean allowDelegatedAccess;

	/*
	 * the user credentials.
	 */
	private UserCredentialsDto userCredentials;

	/*
	 * the custom domains.
	 */
	private List<String> customDomains;

	/*
	 * the indirect reseller associated to this customer account.
	 */
	private String associatedPartnerId;

}

