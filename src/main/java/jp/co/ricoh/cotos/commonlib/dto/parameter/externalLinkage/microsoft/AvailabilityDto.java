package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AvailabilityDto {

	/*
	 * the availability id.
	 */
	private String id;

	/*
	 * the product id.
	 */
	private String productId;

	/*
	 * the sku id.
	 */
	private String skuId;

	/*
	 * the id that uniquely identifies this item in the catalog.
	 */
	private String catalogItemId;

	/*
	 * the default currency supported for this availability.
	 */
	private DefaultCurrencyDto defaultCurrency;

	/*
	 * the segment.
	 */
	private String segment;

	/*
	 * the country.
	 */
	private String country;

	/*
	 * a value indicating whether the availability is purchasable or not.
	 */
	private Boolean isPurchasable;

	/*
	 * a value indicating whether the availability is renewable or not.
	 */
	private Boolean isRenewable;

	/*
	 * the list of renewal instructions.
	 */
	@JsonIgnore
	private String renewalInstructions;

	/*
	 * the terms if applicable to this availability.
	 */
	@JsonIgnore
	private String terms;

	/*
	 * the options for included quantity.
	 */
	@JsonIgnore
	private String includedQuantityOptions;

	/*
	 * the product.
	 */
	@JsonIgnore
	private String product;

	/*
	 * the sku.
	 */
	@JsonIgnore
	private String sku;

}
