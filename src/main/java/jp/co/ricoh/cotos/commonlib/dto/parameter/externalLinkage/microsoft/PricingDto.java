package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PricingDto {

	/*
	 * the list price.
	 */
	private Double listPrice;

	/*
	 * the discounted price.
	 */
	private Double discountedPrice;

	/*
	 * the prorated price.
	 */
	private Double proratedPrice;

	/*
	 * the price.
	 */
	private Double price;

	/*
	 * the extended price.
	 */
	private Double extendedPrice;

}
