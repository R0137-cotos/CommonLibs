package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeO365.CancellationStatus;

@Converter(autoApply = true)
public class CancellationStatusConverter implements AttributeConverter<CancellationStatus, String> {
	@Override
	public String convertToDatabaseColumn(CancellationStatus cancellationStatus) {
		if (cancellationStatus == null)
			return null;
		return cancellationStatus.toString();
	}

	@Override
	public CancellationStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return CancellationStatus.fromString(value);
	}
}
