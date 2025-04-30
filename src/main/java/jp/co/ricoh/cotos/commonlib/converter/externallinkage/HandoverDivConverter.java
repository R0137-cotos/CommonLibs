package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.HandoverMailAddress.HandoverDiv;

@Converter(autoApply = true)
public class HandoverDivConverter implements AttributeConverter<HandoverDiv, String> {

	@Override
	public String convertToDatabaseColumn(HandoverDiv handoverDiv) {
		if (handoverDiv == null)
			return null;
		return handoverDiv.toString();
	}

	@Override
	public HandoverDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return HandoverDiv.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
