package jp.co.ricoh.cotos.commonlib.converter.license;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.license.LicenseProcess.MailSentResultDiv;

@Converter(autoApply = true)
public class MailSentResultDivConverter implements AttributeConverter<MailSentResultDiv, String> {

	@Override
	public String convertToDatabaseColumn(MailSentResultDiv mailSentResultDiv) {
		if (mailSentResultDiv == null)
			return null;
		return mailSentResultDiv.toString();
	}

	@Override
	public MailSentResultDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
<<<<<<< HEAD
		return MailSentResultDiv.fromString(value); // IllegalArgumentExceptionはMailSentResultDiv.fromString側で投げている
	}

}
=======
		return MailSentResultDiv.fromString(value); // IllegalArgumentExceptionはMailSendedResultDiv.fromString側で投げている
	}
}
>>>>>>> e58306266bbe071dbd614745e072bf5cd161a09a
