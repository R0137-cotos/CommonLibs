package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RefundableQuantityDto {

	/*
	 * the total number of seats that are eligible for reduction at the time of the request.
	 */
	private Integer totalQuantity;

	/*
	 * the breakdown of the eligible seats for reduction with their return windows.
	 */
	private RefundableQuantityDetailDto[] details;

}
