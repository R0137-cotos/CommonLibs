package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.AddSubdomainFlg;

@Converter(autoApply = true)
public class AddSubdomainFlgConverter implements AttributeConverter<AddSubdomainFlg, String> {
	@Override
	public String convertToDatabaseColumn(AddSubdomainFlg addSubdomainFlg) {
		if (addSubdomainFlg == null)
			return null;
		return addSubdomainFlg.toString();
	}

	@Override
	public AddSubdomainFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return AddSubdomainFlg.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
