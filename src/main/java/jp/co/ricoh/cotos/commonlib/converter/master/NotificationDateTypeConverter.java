package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.MailControlMaster.NotificationDateType;

@Converter(autoApply = true)
public class NotificationDateTypeConverter implements AttributeConverter<NotificationDateType, String> {

	@Override
	public String convertToDatabaseColumn(NotificationDateType notificationDateType) {
		if (notificationDateType == null)
			return null;
		return notificationDateType.toString();
	}

	@Override
	public NotificationDateType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		//IllegalArgumentExceptionはNotificationDateType.fromString側で投げている
		return NotificationDateType.fromString(value);
	}
}
