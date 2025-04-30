package jp.co.ricoh.cotos.commonlib.converter.contract;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.contract.Contract.ContractStatusControlType;

@Converter(autoApply = true)
public class ContractStatusControlTypeConverter implements AttributeConverter<ContractStatusControlType, String> {
	@Override
	public String convertToDatabaseColumn(ContractStatusControlType contractStatusControlType) {
		if (contractStatusControlType == null)
			return null;
		return contractStatusControlType.toString();
	}

	@Override
	public ContractStatusControlType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ContractStatusControlType.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
