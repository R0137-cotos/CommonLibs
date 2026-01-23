package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ScheduledActionsDto {

	/*
	 *
	 */
	private String scheduleType;

	/*
	 *
	 */
	private String actionType;

	/*
	 *
	 */
	private String effectiveDate;

}
