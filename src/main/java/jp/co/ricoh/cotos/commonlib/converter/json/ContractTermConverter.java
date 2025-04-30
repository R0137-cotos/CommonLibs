package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeO365.ContractTerm;

@Converter(autoApply = true)
public class ContractTermConverter implements AttributeConverter<ContractTerm, String> {
	@Override
	public String convertToDatabaseColumn(ContractTerm contractTerm) {
		if (contractTerm == null)
			return null;
		return contractTerm.toString();
	}

	@Override
	public ContractTerm convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ContractTerm.fromString(value);
	}
}
