package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ApprovalRouteMaster.ContractApprovalRequesterDiv;

@Converter(autoApply = true)
public class ContractApprovalRequesterDivConverter implements AttributeConverter<ContractApprovalRequesterDiv, String> {

	@Override
	public String convertToDatabaseColumn(ContractApprovalRequesterDiv actionDiv) {
		if (actionDiv == null)
			return null;
		return actionDiv.toString();
	}

	@Override
	public ContractApprovalRequesterDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ContractApprovalRequesterDiv.fromString(value); //IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
