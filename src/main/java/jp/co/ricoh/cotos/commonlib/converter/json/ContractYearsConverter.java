package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeMits.ContractYears;

@Converter(autoApply = true)
public class ContractYearsConverter implements AttributeConverter<ContractYears, String> {
	@Override
	public String convertToDatabaseColumn(ContractYears contractYears) {
		if (contractYears == null)
			return null;
		return contractYears.toString();
	}

	@Override
	public ContractYears convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ContractYears.fromString(value);
	}
}