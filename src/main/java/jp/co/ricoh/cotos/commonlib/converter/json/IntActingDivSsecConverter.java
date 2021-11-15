package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeSsec.IntActingDivSsec;

@Converter(autoApply = true)
public class IntActingDivSsecConverter implements AttributeConverter<IntActingDivSsec, String> {
	@Override
	public String convertToDatabaseColumn(IntActingDivSsec newExistingAccountTypeMss) {
		if (newExistingAccountTypeMss == null)
			return null;
		return newExistingAccountTypeMss.toString();
	}

	@Override
	public IntActingDivSsec convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return IntActingDivSsec.fromString(value);
	}
}
