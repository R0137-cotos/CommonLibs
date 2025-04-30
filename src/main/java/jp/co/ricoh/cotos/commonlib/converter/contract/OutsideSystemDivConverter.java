package jp.co.ricoh.cotos.commonlib.converter.contract;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.parameter.communication.ContractSearchParameterForDetailOutsideCotos.OutsideSystemDiv;

@Converter(autoApply = true)
public class OutsideSystemDivConverter implements AttributeConverter<OutsideSystemDiv, String> {
	@Override
	public String convertToDatabaseColumn(OutsideSystemDiv outsideSystemDiv) {
		if (outsideSystemDiv == null)
			return null;
		return outsideSystemDiv.toString();
	}

	@Override
	public OutsideSystemDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OutsideSystemDiv.fromString(value); // IllegalArgumentExceptionはOutsideSystemDiv.fromString側で投げている
	}
}
