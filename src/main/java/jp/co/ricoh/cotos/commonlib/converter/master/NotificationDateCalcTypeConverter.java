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
			return null;
		return NotificationDateCalcType.fromString(value); //IllegalArgumentExceptionはOperationDiv.fromString側で投げている
	}
}
