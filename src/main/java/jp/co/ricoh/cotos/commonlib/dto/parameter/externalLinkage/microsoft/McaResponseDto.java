package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class McaResponseDto extends ResourceBaseWithLinks {

	/*
	 * the customer consent status.
	 * 同意済(true)、未同意(false)
	 */
	private Boolean isSigned;

}
