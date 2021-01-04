package jp.co.ricoh.cotos.commonlib.converter.license;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.license.LicenseProcess.MailSendResultDiv;

@Converter(autoApply = true)
public class MailSendResultDivConverter implements AttributeConverter<MailSendResultDiv, String> {

	@Override
	public String convertToDatabaseColumn(MailSendResultDiv mailSendResultDiv) {
		if (mailSendResultDiv == null)
			return null;
		return mailSendResultDiv.toString();
	}

	@Override
	public MailSendResultDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MailSendResultDiv.fromString(value); // IllegalArgumentExceptionはMailSendResultDiv.fromString側で投げている
	}
}
