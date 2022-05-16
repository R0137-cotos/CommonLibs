package jp.co.ricoh.cotos.commonlib.converter.license;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.license.LicenseInfoExtendsParameterSsecDto.IntActingDiv;

@Converter(autoApply = true)
public class IntActingDivConverter implements AttributeConverter<IntActingDiv, String> {

	@Override
	public String convertToDatabaseColumn(IntActingDiv intActingDiv) {
		if (intActingDiv == null)
			return null;
		return intActingDiv.toString();
	}

	@Override
	public IntActingDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return IntActingDiv.fromString(value); // IllegalArgumentExceptionはInfoDiv.fromString側で投げている
	}

}
