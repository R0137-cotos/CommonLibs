package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TransitionEligibilityDetailDto {

	/*
	 * a value indicating whether the transition is eligible.
	 */
	private Boolean isEligible;

	/*
	 * the transition type.
	 */
	private String transitionType;

	/*
	 * the reasons the transition cannot be performed, if applicable.
	 */
	private TransitionErrorDto[] errors;

}
