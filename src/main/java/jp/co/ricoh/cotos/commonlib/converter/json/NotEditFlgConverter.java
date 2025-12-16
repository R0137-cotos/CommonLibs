package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.NotEditFlg;

@Converter(autoApply = true)
public class NotEditFlgConverter implements AttributeConverter<NotEditFlg, String> {
	@Override
	public String convertToDatabaseColumn(NotEditFlg notEditFlg) {
		if (notEditFlg == null)
			return null;
		return notEditFlg.toString();
	}

	@Override
	public NotEditFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return NotEditFlg.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
