package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper=false)
public class SubscriptionRequestDto extends ResourceBase {

	/*
	 * the quantity.
	 */
	private Integer quantity;

	/*
	 * the subscription status.
	 */
	private String status;

	/*
	 * a value indicating whether automatic renew is enabled or not.
	 */
	private Boolean autoRenewEnabled;

	/*
	 * the next term instructions for scheduled changes during the next auto renewal (not applicable for legacy offers).
	 */
	private ScheduledNextTermInstructionsDto scheduledNextTermInstructions;

	/*
	 *
	 */
	private ScheduledActionsDto scheduledActions;

}
