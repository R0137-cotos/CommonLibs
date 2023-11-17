package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ResourceBaseWithLinks extends ResourceBase {

	/*
	 * the resource links.
	 */
	private StandardResourceLinks links;

}
