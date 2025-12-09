package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.AddMailDeleteFlg;

@Converter(autoApply = true)
public class AddMailDeleteFlgConverter implements AttributeConverter<AddMailDeleteFlg, String> {
	@Override
	public String convertToDatabaseColumn(AddMailDeleteFlg addMailDeleteFlg) {
		if (addMailDeleteFlg == null)
			return null;
		return addMailDeleteFlg.toString();
	}

	@Override
	public AddMailDeleteFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return AddMailDeleteFlg.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
