package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CustomerCompanyProfileDto extends ResourceBaseWithLinks {

	/*
	 * the azure active directory tenant identifier for the customer's tenant.
	 */
	private String tenantId;

	/*
	 * the customer's azure active directory domain.
	 */
	private String domain;

	/*
	 * the name of the company.
	 */
	private String companyName;

	/*
	 * the organization registration number.
	 */
	private String organizationRegistrationNumber;

}
