package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ResourceBase extends AbstractMsResponseDto {

	/*
	 * the resource attributes.
	 */
	private AttributesDto attributes;

}
