package jp.co.ricoh.cotos.commonlib.converter.contract;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.ContractInfoChangeAddOnProcType;

@Converter(autoApply = true)
public class ContractInfoChangeAddOnProcTypeConverter implements AttributeConverter<ContractInfoChangeAddOnProcType, String> {
	@Override
	public String convertToDatabaseColumn(ContractInfoChangeAddOnProcType contractInfoChangeAddOnProcType) {
		if (contractInfoChangeAddOnProcType == null)
			return null;
		return contractInfoChangeAddOnProcType.toString();
	}

	@Override
	public ContractInfoChangeAddOnProcType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ContractInfoChangeAddOnProcType.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
