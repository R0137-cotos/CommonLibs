package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductTypeDto {

	/*
	 * the id.
	 */
	private String id;

	/*
	 * the display name.
	 */
	private String displayName;

}
