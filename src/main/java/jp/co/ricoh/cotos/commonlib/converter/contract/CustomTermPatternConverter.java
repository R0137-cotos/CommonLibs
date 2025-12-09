package jp.co.ricoh.cotos.commonlib.converter.contract;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeO365.CustomTermPattern;

@Converter(autoApply = true)
public class CustomTermPatternConverter implements AttributeConverter<CustomTermPattern, String> {
	@Override
	public String convertToDatabaseColumn(CustomTermPattern changeTermPattern) {
		if (changeTermPattern == null)
			return null;
		return changeTermPattern.toString();
	}

	@Override
	public CustomTermPattern convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return CustomTermPattern.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
