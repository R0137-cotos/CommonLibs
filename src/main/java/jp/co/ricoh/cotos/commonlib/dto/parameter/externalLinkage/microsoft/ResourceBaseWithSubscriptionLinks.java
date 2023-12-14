package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ResourceBaseWithSubscriptionLinks extends ResourceBase {

	/*
	 * the resource links.
	 */
	private SubscriptionResourceLinks links;

}
