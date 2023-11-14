package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TransitionEligibilityDto extends ResourceBase {

	/*
	 * the catalog item id.
	 */
	private String catalogItemId;

	/*
	 * the SKU title.
	 */
	private String title;

	/*
	 * the SKU description.
	 */
	private String description;

	/*
	 * the quantity of the new offer to be purchased.
	 */
	private String quantity;

	/*
	 * the eligibilities.
	 */
	private TransitionEligibilityDetailDto[] eligibilities;

}
