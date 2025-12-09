package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.SajitsutenFlg;

@Converter(autoApply = true)
public class SajitsutenFlgConverter implements AttributeConverter<SajitsutenFlg, String> {
	@Override
	public String convertToDatabaseColumn(SajitsutenFlg sajitsutenFlg) {
		if (sajitsutenFlg == null)
			return null;
		return sajitsutenFlg.toString();
	}

	@Override
	public SajitsutenFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return SajitsutenFlg.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
