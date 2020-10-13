package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ArrangementWorkOrderMaster.CheckTimingType;

@Converter(autoApply = true)
public class CheckTimingTypeConverter implements AttributeConverter<CheckTimingType, String> {

	@Override
	public String convertToDatabaseColumn(CheckTimingType checkTimingType) {
		if (checkTimingType == null)
			return null;
		return checkTimingType.toString();
	}

	@Override
	public CheckTimingType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return CheckTimingType.fromString(value); //IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}