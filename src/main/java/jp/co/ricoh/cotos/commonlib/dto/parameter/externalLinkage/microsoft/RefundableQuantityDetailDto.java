package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RefundableQuantityDetailDto {

	/*
	 * the number of seats that are eligible for reduction for the given window.
	 */
	private Integer quantity;

	/*
	 * the date and time until which the seats are eligible for reduction.
	 */
	private String allowedUntilDateTime;

}
