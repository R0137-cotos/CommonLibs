package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class OrderResourceLinks extends AbstractMsResponseDto {

	/*
	 * the link to the provisioning status of an order.
	 */
	private Link provisioningStatus;

	/*
	 * the link to the patch operation.
	 */
	private Link patchOperation;

	/*
	 * the self URI.
	 */
	private Link self;

}
