package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.MailAddressMaster.MailAddressDiv;

@Converter(autoApply = true)
public class MailAddressDivConverter implements AttributeConverter<MailAddressDiv, String> {

	@Override
	public String convertToDatabaseColumn(MailAddressDiv mailAddressDiv) {
		if (mailAddressDiv == null)
			return null;
		return mailAddressDiv.toString();
	}

	@Override
	public MailAddressDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return MailAddressDiv.fromString(value); //IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}