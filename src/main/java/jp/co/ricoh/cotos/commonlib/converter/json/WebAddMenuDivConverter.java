package jp.co.ricoh.cotos.commonlib.converter.json;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.WebAddMenuDiv;

@Converter(autoApply = true)
public class WebAddMenuDivConverter implements AttributeConverter<WebAddMenuDiv, String> {
	@Override
	public String convertToDatabaseColumn(WebAddMenuDiv webAddMenuDiv) {
		if (webAddMenuDiv == null)
			return null;
		return webAddMenuDiv.toString();
	}

	@Override
	public WebAddMenuDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return WebAddMenuDiv.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
