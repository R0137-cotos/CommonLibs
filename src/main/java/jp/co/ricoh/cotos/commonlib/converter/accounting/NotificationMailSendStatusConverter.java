package jp.co.ricoh.cotos.commonlib.converter.accounting;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.accounting.UsageQuantity.NotificationMailSendStatus;

@Converter(autoApply = true)
public class NotificationMailSendStatusConverter implements AttributeConverter<NotificationMailSendStatus, String> {
	@Override
	public String convertToDatabaseColumn(NotificationMailSendStatus notificationMailSendStatus) {
		if (notificationMailSendStatus == null)
			return null;
		return notificationMailSendStatus.toString();
	}

	@Override
	public NotificationMailSendStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return NotificationMailSendStatus.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
