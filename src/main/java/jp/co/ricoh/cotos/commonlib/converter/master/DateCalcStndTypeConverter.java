package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.DateCalcPatternMaster.DateCalcStndType;

@Converter(autoApply = true)
public class DateCalcStndTypeConverter implements AttributeConverter<DateCalcStndType, String> {

	@Override
	public String convertToDatabaseColumn(DateCalcStndType dateCalcStndType) {
		if (dateCalcStndType == null)
			return null;
		return dateCalcStndType.toString();
	}

	@Override
	public DateCalcStndType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return DateCalcStndType.fromString(value); //IllegalArgumentExceptionはDateCalcStndType.fromString側で投げている
	}
}