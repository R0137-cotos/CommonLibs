package jp.co.ricoh.cotos.commonlib.converter.communication;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.communication.BounceMailDestination.DestinationDiv;

@Converter(autoApply = true)
public class DestinationDivConverter implements AttributeConverter<DestinationDiv, String> {
	@Override
	public String convertToDatabaseColumn(DestinationDiv destinationDiv) {
		if (destinationDiv == null)
			return null;
		return destinationDiv.toString();
	}

	@Override
	public DestinationDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return DestinationDiv.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
