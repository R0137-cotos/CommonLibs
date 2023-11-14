package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class LineItemsDto {

	/*
	 * the line item number.
	 */
	private Integer lineItemNumber;

	/*
	 * the offer identifier.
	 */
	private String offerId;

	/*
	 * the friendly name for the result contract (subscription).
	 */
	private String friendlyName;

	/*
	 * the product quantity.
	 */
	private Integer quantity;

	/*
	 * the provisioning context for the offer.
	 */
	private HashMap<String, String> provisioningContext;

	/*
	 * the resulting subscription identifier.
	 */
	private String subscriptionId;

	/*
	 * the parent subscription identifier.
	 */
	private String parentSubscriptionId;

	/*
	 * the term duration.
	 */
	private String termDuration;

	/*
	 * the RenewsTo.
	 */
	private RenewsToDto renewsTo;

	/*
	 * the transaction type.
	 */
	private String transactionType;

	/*
	 * the promotion id.
	 */
	private String promotionId;

	/*
	 * the custom term end date.
	 */
	private String customTermEndDate;

	/*
	 * the pricing details.
	 */
	private PricingDto pricing;

}
