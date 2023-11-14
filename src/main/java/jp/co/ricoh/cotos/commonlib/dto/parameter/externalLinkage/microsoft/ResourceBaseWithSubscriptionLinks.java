package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import lombok.Data;

@Data
public class ResourceBaseWithSubscriptionLinks extends ResourceBase {

	/*
	 * the resource links.
	 */
	private SubscriptionResourceLinks links;

}
