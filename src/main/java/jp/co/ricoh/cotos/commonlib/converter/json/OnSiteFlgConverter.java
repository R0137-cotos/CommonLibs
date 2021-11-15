package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.OnSiteFlg;

@Converter(autoApply = true)
public class OnSiteFlgConverter implements AttributeConverter<OnSiteFlg, String> {
	@Override
	public String convertToDatabaseColumn(OnSiteFlg newExistingAccountTypeMss) {
		if (newExistingAccountTypeMss == null)
			return null;
		return newExistingAccountTypeMss.toString();
	}

	@Override
	public OnSiteFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OnSiteFlg.fromString(value);
	}
}
