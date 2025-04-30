package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.ContractTypeDetails;

@Converter(autoApply = true)
public class ContractTypeDetailsConverter implements AttributeConverter<ContractTypeDetails, String> {
	@Override
	public String convertToDatabaseColumn(ContractTypeDetails contractTypeDetails) {
		if (contractTypeDetails == null)
			return null;
		return contractTypeDetails.toString();
	}

	@Override
	public ContractTypeDetails convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ContractTypeDetails.fromString(value);
	}
}
