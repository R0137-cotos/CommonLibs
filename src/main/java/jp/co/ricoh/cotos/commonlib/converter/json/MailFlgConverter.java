package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.MailFlg;

@Converter(autoApply = true)
public class MailFlgConverter implements AttributeConverter<MailFlg, String> {
	@Override
	public String convertToDatabaseColumn(MailFlg mailFlg) {
		if (mailFlg == null)
			return null;
		return mailFlg.toString();
	}

	@Override
	public MailFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MailFlg.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
