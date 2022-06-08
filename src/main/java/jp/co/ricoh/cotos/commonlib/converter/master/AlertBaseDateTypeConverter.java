package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ApprovalAlertManagementMaster.AlertBaseDateType;

@Converter(autoApply = true)
public class AlertBaseDateTypeConverter implements AttributeConverter<AlertBaseDateType, String> {

	@Override
	public String convertToDatabaseColumn(AlertBaseDateType alertBaseDateType) {
		if (alertBaseDateType == null)
			return null;
		return alertBaseDateType.toString();
	}

	@Override
	public AlertBaseDateType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		// IllegalArgumentExceptionはAlertTargetDateType.fromString側で投げている
		return AlertBaseDateType.fromString(value);
	}
}
