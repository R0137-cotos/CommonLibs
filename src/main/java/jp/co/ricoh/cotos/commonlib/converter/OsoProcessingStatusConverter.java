package jp.co.ricoh.cotos.commonlib.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.OsoProcessingStatus;

@Converter(autoApply = true)
public class OsoProcessingStatusConverter implements AttributeConverter<OsoProcessingStatus, String> {

	@Override
	public String convertToDatabaseColumn(OsoProcessingStatus osoProcessingStatus) {
		if (osoProcessingStatus == null)
			return null;
		return osoProcessingStatus.toString();
	}

	@Override
	public OsoProcessingStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OsoProcessingStatus.fromString(value); // IllegalArgumentExceptionはOsoProcessingStatus.fromString側で投げている
	}

}
