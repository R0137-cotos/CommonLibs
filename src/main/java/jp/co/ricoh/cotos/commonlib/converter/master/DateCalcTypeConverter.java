package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.DateCalcPatternMaster.DateCalcType;

@Converter(autoApply = true)
public class DateCalcTypeConverter implements AttributeConverter<DateCalcType, String> {

	@Override
	public String convertToDatabaseColumn(DateCalcType dateCalcType) {
		if (dateCalcType == null)
			return null;
		return dateCalcType.toString();
	}

	@Override
	public DateCalcType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return DateCalcType.fromString(value); //IllegalArgumentExceptionはDateCalcType.fromString側で投げている
	}
}