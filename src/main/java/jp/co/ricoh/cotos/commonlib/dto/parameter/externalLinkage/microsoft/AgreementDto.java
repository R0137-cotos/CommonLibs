package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper=false)
public class AgreementDto extends AbstractMsResponseDto {

	/*
	 * the partner's user ID.
	 */
	private String userId;

	/*
	 * the primary contact for the agreement.
	 */
	private PrimaryContactDto primaryContact;

	/*
	 * the template ID of the agreement.
	 */
	private String templateId;

	/*
	 * the date the agreement was signed.
	 */
	private String dateAgreed;

	/*
	 * the agreement type.
	 */
	private String type;

	/*
	 * the download link for the agreement.
	 */
	private String AgreementLink;

}
