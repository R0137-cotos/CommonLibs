package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AgreementDto {

	/*
	 * the partner's user ID.
	 */
	private String userId;

	/*
	 * the primary contact for the agreement.
	 */
	private PrimaryContactDto primaryContact;

}
