package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DefaultCurrencyDto {

	/*
	 * the code of the currency.
	 */
	private String code;

	/*
	 * the symbol of the currency.
	 */
	private String symbol;

}
