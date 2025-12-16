package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ContractChangeSpanMaster.CheckPatternType;

@Converter(autoApply = true)
public class CheckPatternTypeConverter implements AttributeConverter<CheckPatternType, String> {

	@Override
	public String convertToDatabaseColumn(CheckPatternType checkPatternType) {
		if (checkPatternType == null)
			return null;
		return checkPatternType.toString();
	}

	@Override
	public CheckPatternType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return CheckPatternType.fromString(value); //IllegalArgumentExceptionはCheckPatternType.fromString側で投げている
	}
}