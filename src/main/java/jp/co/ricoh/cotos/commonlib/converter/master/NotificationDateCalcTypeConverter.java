package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.MailControlMaster.NotificationDateCalcType;

@Converter(autoApply = true)
public class NotificationDateCalcTypeConverter implements AttributeConverter<NotificationDateCalcType, String> {

	@Override
	public String convertToDatabaseColumn(NotificationDateCalcType notificationDateCalcType) {
		if (notificationDateCalcType == null)
			return null;
		return notificationDateCalcType.toString();
	}

	@Override
	public NotificationDateCalcType convertToEntityAttribute(String value) {
		if (value == null)
			return NotificationDateCalcType.未設定;
		return NotificationDateCalcType.fromString(value); //IllegalArgumentExceptionはNotificationDateCalcType.fromString側で投げている
	}
}
