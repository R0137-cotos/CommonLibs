package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.WebFlg;

@Converter(autoApply = true)
public class WebFlgConverter implements AttributeConverter<WebFlg, String> {
	@Override
	public String convertToDatabaseColumn(WebFlg webFlg) {
		if (webFlg == null)
			return null;
		return webFlg.toString();
	}

	@Override
	public WebFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return WebFlg.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
