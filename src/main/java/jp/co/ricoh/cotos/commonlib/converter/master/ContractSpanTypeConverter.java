package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.ContractSpanType;

@Converter(autoApply = true)
public class ContractSpanTypeConverter implements AttributeConverter<ContractSpanType, String> {
	@Override
	public String convertToDatabaseColumn(ContractSpanType contractSpanType) {
		if (contractSpanType == null)
			return null;
		return contractSpanType.toString();
	}

	@Override
	public ContractSpanType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ContractSpanType.fromString(value); //IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
