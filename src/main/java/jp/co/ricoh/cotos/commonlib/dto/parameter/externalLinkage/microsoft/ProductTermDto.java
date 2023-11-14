package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductTermDto {

	/*
	 * the future product id.
	 */
	private String productId;

	/*
	 * the future SKU id.
	 */
	private String skuId;

	/*
	 * the future availability id.
	 */
	private String availabilityId;

	/*
	 * the type of billing cycle for the selected offers.
	 */
	private String billingCycle;

	/*
	 * the term duration if applicable.
	 */
	private String termDuration;

	/*
	 * the promotion id.
	 */
	private String promotionId;

}
