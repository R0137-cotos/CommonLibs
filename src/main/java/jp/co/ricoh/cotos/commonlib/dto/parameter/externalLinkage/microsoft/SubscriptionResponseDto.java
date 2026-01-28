package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper=false)
public class SubscriptionResponseDto extends ResourceBaseWithSubscriptionLinks {

	/*
	 * the subscription identifier.
	 */
	private String id;

	/*
	 * the entitlement identifier.
	 */
	private String entitlementId;

	/*
	 * the offer identifier.
	 */
	private String offerId;

	/*
	 * the offer name.
	 */
	private String offerName;

	/*
	 * the friendly name for the subscription.
	 */
	private String friendlyName;

	/*
	 * the product type for the subscription.
	 */
	private ProductTypeDto productType;

	/*
	 * the quantity.
	 */
	private Integer quantity;

	/*
	 * the units defining Quantity for the subscription.
	 */
	private String unitType;

	/*
	 * the parent subscription identifier.
	 */
	private String parentSubscriptionId;

	/*
	 * the consumption type.
	 */
	private String consumptionType;

	/*
	 * the creation date.
	 */
	private String creationDate;

	/*
	 * the effective start date for this subscription.
	 */
	private String effectiveStartDate;

	/*
	 * the commitment end date for this subscription.
	 */
	private String commitmentEndDate;

	/*
	 * the commitment end date time for this subscription.
	 */
	private String commitmentEndDateTime;

	/*
	 * the last date that the partner can cancel their subscription for the given term, in UTC date-time format.
	 */
	private String cancellationAllowedUntilDate;

	/*
	 * the billing cycle end date for term-based subscription.
	 */
	private String billingCycleEndDate;

	/*
	 * the billing cycle end date time for term-based subscription.
	 */
	private String billingCycleEndDateTime;

	/*
	 * the subscription status.
	 */
	private String status;

	/*
	 * a value indicating whether automatic renew is enabled or not.
	 */
	private Boolean autoRenewEnabled;

	/*
	 * the billing type.
	 */
	private String billingType;

	/*
	 * the billing cycle.
	 */
	private String billingCycle;

	/*
	 * a value indicating whether the subscription has purchasable add-ons.
	 */
	private Boolean hasPurchasableAddons;

	/*
	 * a value indicating whether the subscription is a trial.
	 */
	private Boolean isTrial;

	/*
	 * the actions.
	 */
	private String[] actions;

	/*
	 * the term duration.
	 */
	private String termDuration;

	/*
	 * the renewal term duration.
	 */
	private String renewalTermDuration;

	/*
	 * the refund options for this subscription if applicable.
	 */
	private RefundOptionDto[] refundOptions;

	/*
	 * a value indicating whether gets or sets the value indicating that the availability is a Microsoft product.
	 */
	private Boolean isMicrosoftProduct;

	/*
	 * the type of contract.
	 */
	private String contractType;

	/*
	 * the publisher name.
	 */
	private String publisherName;

	/*
	 * the promotion id.
	 */
	private String promotionId;

	/*
	 * the ID of the Traditional-Commerce subscription which the New-Commerce subscription was migrated from.
	 */
	private String migratedFromSubscriptionId;

	/*
	 * the number of seats eligible for reduction at the time of the request.
	 */
	private RefundableQuantityDto refundableQuantity;

	/*
	 * the next term instructions for scheduled changes during the next auto renewal (not applicable for legacy offers).
	 */
	private ScheduledNextTermInstructionsDto scheduledNextTermInstructions;

	/*
	 * the next charge instructions for billing cycle changes during the next charge cycle (not applicable for legacy offers).
	 */
	private NextChargeInstructionsDto nextChargeInstructions;

	/*
	 *
	 */
	private ScheduledActionsDto scheduledActions;

}
