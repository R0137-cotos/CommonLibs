package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TransitionDto extends ResourceBase {

	/*
	 * the operation id.
	 */
	private String operationId;

	/*
	 * the From catalog item id.
	 */
	private String fromCatalogItemId;

	/*
	 * the from subscription id.
	 */
	private String fromSubscriptionId;

	/*
	 * the To catalog item id.
	 */
	private String toCatalogItemId;

	/*
	 * the To subscription id.
	 */
	private String toSubscriptionId;

	/*
	 * the quantity being transitioned to the target catalog item.
	 */
	private Integer quantity;

	/*
	 * the term duration.
	 */
	private String termDuration;

	/*
	 * a value indicating the frequency with which the partner is billed for the target subscription.
	 */
	private String billingCycle;

	/*
	 * the transition type.
	 */
	private String transitionType;

	/*
	 * the promotionId.
	 */
	private String promotionId;

	/*
	 * the events of the transtion.
	 */
	private TransitionEventDto[] events;

}
