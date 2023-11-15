package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ScheduledNextTermInstructionsDto {

	/*
	 * the future product and term ID.
	 */
	private ProductTermDto product;

	/*
	 * the quantity to convert to at the next term.
	 */
	private Integer quantity;

	/*
	 * the custom term end date if applicable.
	 */
	private String customTermEndDate;

}
