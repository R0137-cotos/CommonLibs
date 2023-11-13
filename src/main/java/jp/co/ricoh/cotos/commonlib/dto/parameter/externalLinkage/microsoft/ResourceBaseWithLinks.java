package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import lombok.Data;

@Data
public class ResourceBaseWithLinks extends ResourceBase {

	/*
	 * the resource links.
	 */
	private StandardResourceLinks links;

}
