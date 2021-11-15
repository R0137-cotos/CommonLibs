package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.OnsiteOnlyFlg;

@Converter(autoApply = true)
public class OnsiteOnlyFlgConverter implements AttributeConverter<OnsiteOnlyFlg, String> {
	@Override
	public String convertToDatabaseColumn(OnsiteOnlyFlg newExistingAccountTypeMss) {
		if (newExistingAccountTypeMss == null)
			return null;
		return newExistingAccountTypeMss.toString();
	}

	@Override
	public OnsiteOnlyFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OnsiteOnlyFlg.fromString(value);
	}
}
