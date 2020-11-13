package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.LicenseProcessPatternMaster.MailDiv;

@Converter(autoApply = true)
public class MailDivConberter implements AttributeConverter<MailDiv, String> {

	@Override
	public String convertToDatabaseColumn(MailDiv mailDiv) {
		if (mailDiv == null)
			return null;
		return mailDiv.toString();
	}

	@Override
	public MailDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MailDiv.fromString(value); // IllegalArgumentExceptionはInfoDiv.fromString側で投げている
	}
}
