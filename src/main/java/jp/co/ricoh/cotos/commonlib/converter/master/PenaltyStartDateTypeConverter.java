package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.ItemMaster.PenaltyStartDateType;

@Converter(autoApply = true)
public class PenaltyStartDateTypeConverter implements AttributeConverter<PenaltyStartDateType, String> {

	@Override
	public String convertToDatabaseColumn(PenaltyStartDateType penaltyStartDateType) {
		if (penaltyStartDateType == null)
			return null;
		return penaltyStartDateType.toString();
	}

	@Override
	public PenaltyStartDateType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return PenaltyStartDateType.fromString(value); //IllegalArgumentExceptionはPenaltyStartDateType.fromString側で投げている
	}
}