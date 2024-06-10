package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.PriceRevisionDateMaster.DifferenceCalcDiv;


@Converter(autoApply = true)
public class DifferenceCalcDivConverter implements AttributeConverter<DifferenceCalcDiv, String> {

	@Override
	public String convertToDatabaseColumn(DifferenceCalcDiv differenceCalcDiv) {
		if (differenceCalcDiv == null)
			return null;
		return differenceCalcDiv.toString();
	}

	@Override
	public DifferenceCalcDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return DifferenceCalcDiv.fromString(value); //IllegalArgumentExceptionはDifferenceCalcDiv.fromString側で投げている
	}
}