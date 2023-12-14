package jp.co.ricoh.cotos.commonlib.dto.parameter.externalLinkage.microsoft;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@EqualsAndHashCode(callSuper=false)
public class AgreementsDto extends ResourceBase {

	/*
	 * the total count of the elements in the resource collection.
	 */
	private Integer totalCount;

	/*
	 * the collection items.
	 */
	private AgreementDto[] items;

}
