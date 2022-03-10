package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ApprovalAlertManagementMaster.AlertTargetDateType;

@Converter(autoApply = true)
public class AlertTargetDateTypeConverter implements AttributeConverter<AlertTargetDateType, String> {

	@Override
	public String convertToDatabaseColumn(AlertTargetDateType alertTargetDateType) {
		if (alertTargetDateType == null)
			return null;
		return alertTargetDateType.toString();
	}

	@Override
	public AlertTargetDateType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return AlertTargetDateType.fromString(value); //IllegalArgumentExceptionはContractChangeSpanTargetDateType.fromString側で投げている
	}
}