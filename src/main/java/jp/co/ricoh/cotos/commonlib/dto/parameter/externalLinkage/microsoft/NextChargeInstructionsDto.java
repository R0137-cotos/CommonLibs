package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class NextChargeInstructionsDto {

	/*
	 * the future product and term ID.
	 */
	private ProductTermDto product;

	/*
	 * the quantity to convert to at the next charge cycle.
	 */
	private Integer quantity;

}
