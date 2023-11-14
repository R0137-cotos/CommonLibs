package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class RefundOptionDto {

	/*
	 * the type of refund ("Full, Partial")
	 */
	private String type;

	/*
	 * the timestamp when this policy expires if applicable, null otherwise.
	 */
	private String expiresAt;

}
