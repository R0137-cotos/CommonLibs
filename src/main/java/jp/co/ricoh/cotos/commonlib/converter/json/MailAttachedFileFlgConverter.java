package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypeRitosExitB.MailAttachedFileFlg;

@Converter(autoApply = true)
public class MailAttachedFileFlgConverter implements AttributeConverter<MailAttachedFileFlg, String> {
	@Override
	public String convertToDatabaseColumn(MailAttachedFileFlg mailAttachedFileFlg) {
		if (mailAttachedFileFlg == null)
			return null;
		return mailAttachedFileFlg.toString();
	}

	@Override
	public MailAttachedFileFlg convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MailAttachedFileFlg.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
