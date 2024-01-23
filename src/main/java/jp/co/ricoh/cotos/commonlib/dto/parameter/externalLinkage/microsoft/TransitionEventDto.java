package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper=false)
public class TransitionEventDto extends ResourceBase {

	/*
	 * the name of the transition event.
	 */
	private String name;

	/*
	 * the status of transition event.
	 */
	private String status;

	/*
	 * the UTC timestamp of the event.
	 */
	private String timestamp;

	/*
	 * the transition errors.S
	 */
	private TransitionErrorDto[] errors;

}
