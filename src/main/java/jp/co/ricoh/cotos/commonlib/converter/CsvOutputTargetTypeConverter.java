package jp.co.ricoh.cotos.commonlib.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.CsvOutputTargetType;

@Converter(autoApply = true)
public class CsvOutputTargetTypeConverter implements AttributeConverter<CsvOutputTargetType, String> {

	@Override
	public String convertToDatabaseColumn(CsvOutputTargetType csvOutputTargetType) {
		if (csvOutputTargetType == null)
			return null;
		return csvOutputTargetType.toString();
	}

	@Override
	public CsvOutputTargetType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return CsvOutputTargetType.fromString(value); // IllegalArgumentExceptionはCsvOutputTargetType.fromString側で投げている
	}
}