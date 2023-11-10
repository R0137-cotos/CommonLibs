package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class ResorceBase {

	/*
	 * the resource attributes.
	 */
	private static Attributes attributes;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Data
	public class Attributes {

		/*
		 * the etag.
		 */
		private String etag;

		/*
		 * the object type.
		 */
		private String objectType;
	}
}
