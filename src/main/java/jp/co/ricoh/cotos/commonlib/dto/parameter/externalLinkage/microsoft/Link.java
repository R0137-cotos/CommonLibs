package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Link {

	/*
	 * the URI.
	 */
	private String uri;

	/*
	 * the method.
	 */
	private String method;

	/*
	 * the link headers.
	 */
	private List<Map.Entry<String, String>> headers;

}
