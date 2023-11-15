package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import lombok.Data;

@Data
public class SubscriptionResourceLinks {

	/*
	 * the product Link.
	 */
	private Link product;

	/*
	 * the sku link.
	 */
	private Link sku;

	/*
	 * the availability link.
	 */
	private Link availability;

	/*
	 * the self URI.
	 */
	private Link self;

}
