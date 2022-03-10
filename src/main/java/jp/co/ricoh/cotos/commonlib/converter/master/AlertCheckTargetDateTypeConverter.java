package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ContractChangeSpanMaster.AlertCheckTargetDateType;

@Converter(autoApply = true)
public class AlertCheckTargetDateTypeConverter implements AttributeConverter<AlertCheckTargetDateType, String> {

	@Override
	public String convertToDatabaseColumn(AlertCheckTargetDateType alertCheckTargetDateType) {
		if (alertCheckTargetDateType == null)
			return null;
		return alertCheckTargetDateType.toString();
	}

	@Override
	public AlertCheckTargetDateType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return AlertCheckTargetDateType.fromString(value); //IllegalArgumentExceptionはContractChangeSpanTargetDateType.fromString側で投げている
	}
}