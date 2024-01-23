package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AgreementMetaDataDto {

	/*
	 * the template ID for the agreement.
	 */
	private String templateId;

	/*
	 * the agreement type.
	 */
	private String agreementType;

	/*
	 * the download link for the agreement.
	 */
	private String agreementLink;

	/*
	 * the ranking for the version for enforcement.
	 */
	private Integer versionRank;

}
