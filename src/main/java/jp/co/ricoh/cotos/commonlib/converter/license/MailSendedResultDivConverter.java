package jp.co.ricoh.cotos.commonlib.converter.license;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.license.LicenseProcess.MailSendedResultDiv;

@Converter(autoApply = true)
public class MailSendedResultDivConverter implements AttributeConverter<MailSendedResultDiv, String> {

	@Override
	public String convertToDatabaseColumn(MailSendedResultDiv mailSendedResultDiv) {
		if (mailSendedResultDiv == null)
			return null;
		return mailSendedResultDiv.toString();
	}

	@Override
	public MailSendedResultDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MailSendedResultDiv.fromString(value); // IllegalArgumentExceptionはMailSendedResultDiv.fromString側で投げている
	}

}
