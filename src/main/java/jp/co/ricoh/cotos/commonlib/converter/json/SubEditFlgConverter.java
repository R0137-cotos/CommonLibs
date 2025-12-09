package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.SubEditFlg;

@Converter(autoApply = true)
public class SubEditFlgConverter implements AttributeConverter<SubEditFlg, String> {
	@Override
	public String convertToDatabaseColumn(SubEditFlg subEditFlg) {
		if (subEditFlg == null)
			return null;
		return subEditFlg.toString();
	}

	@Override
	public SubEditFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return SubEditFlg.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
