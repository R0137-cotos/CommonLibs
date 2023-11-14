package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OrderDto extends OrderResourceLinks {

	/*
	 * the reference customer identifier.
	 */
	private String referenceCustomerId;

	/*
	 * the type of billing cycle for the selected offers.
	 */
	private String billingCycle;

	/*
	 * the Order line items.
	 */
	private LineItemsDto lineItems;

	/*
	 * the order identifier.
	 */
	private String id;

	/*
	 * the alternate order identifier.
	 */
	private String alternateId;

	/*
	 * the currency code.
	 */
	private String currencyCode;

	/*
	 * the currency symbol.
	 */
	private String currencySymbol;

	/*
	 * the creation date of the order.
	 */
	private String creationDate;

	/*
	 * the order status.
	 */
	private String status;

	/*
	 * the transaction type.
	 */
	private String transactionType;

	/*
	 * the total price for the order.
	 */
	private Double totalPrice;

	/*
	 * a value indicating whether the PartnerOnRecord attestation was accepted.
	 */
	private Boolean partnerOnRecordAttestationAccepted;

}
