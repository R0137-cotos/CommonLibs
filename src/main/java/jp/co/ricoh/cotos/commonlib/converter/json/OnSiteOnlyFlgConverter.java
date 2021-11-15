package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumType.OnSiteOnlyFlg;

@Converter(autoApply = true)
public class OnSiteOnlyFlgConverter implements AttributeConverter<OnSiteOnlyFlg, String> {
	@Override
	public String convertToDatabaseColumn(OnSiteOnlyFlg newExistingAccountTypeMss) {
		if (newExistingAccountTypeMss == null)
			return null;
		return newExistingAccountTypeMss.toString();
	}

	@Override
	public OnSiteOnlyFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return OnSiteOnlyFlg.fromString(value);
	}
}
