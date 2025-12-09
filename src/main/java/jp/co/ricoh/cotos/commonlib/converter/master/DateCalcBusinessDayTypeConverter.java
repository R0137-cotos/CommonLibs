package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.DateCalcPatternMaster.DateCalcBusinessDayType;

@Converter(autoApply = true)
public class DateCalcBusinessDayTypeConverter implements AttributeConverter<DateCalcBusinessDayType, String> {

	@Override
	public String convertToDatabaseColumn(DateCalcBusinessDayType dateCalcBusinessDayType) {
		if (dateCalcBusinessDayType == null)
			return null;
		return dateCalcBusinessDayType.toString();
	}

	@Override
	public DateCalcBusinessDayType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return DateCalcBusinessDayType.fromString(value); //IllegalArgumentExceptionはDateCalcBusinessDayType.fromString側で投げている
	}
}