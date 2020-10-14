package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.MailControlMaster.NotificatnDataDifferenceType;

@Converter(autoApply = true)
public class NotificatnDataDifferenceTypeConverter implements AttributeConverter<NotificatnDataDifferenceType, String> {

	@Override
	public String convertToDatabaseColumn(NotificatnDataDifferenceType notificatnDataDifferenceType) {
		if (notificatnDataDifferenceType == null)
			return null;
		return notificatnDataDifferenceType.toString();
	}

	@Override
	public NotificatnDataDifferenceType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return NotificatnDataDifferenceType.fromString(value); //IllegalArgumentExceptionはNotificatnDataDifferenceType.fromString側で投げている
	}
}