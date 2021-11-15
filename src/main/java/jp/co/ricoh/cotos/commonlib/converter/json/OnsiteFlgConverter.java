package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.OnsiteFlg;

@Converter(autoApply = true)
public class OnsiteFlgConverter implements AttributeConverter<OnsiteFlg, String> {
	@Override
	public String convertToDatabaseColumn(OnsiteFlg newExistingAccountTypeMss) {
		if (newExistingAccountTypeMss == null)
			return null;
		return newExistingAccountTypeMss.toString();
	}

	@Override
	public OnsiteFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OnsiteFlg.fromString(value);
	}
}
