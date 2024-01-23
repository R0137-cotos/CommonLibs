package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TransitionErrorDto {

	/*
	 * the error code associated with the issue.
	 */
	private String code;

	/*
	 * friendly text describing the error.
	 */
	private String description;

}
