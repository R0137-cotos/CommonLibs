package jp.co.ricoh.cotos.commonlib.converter.estimation;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.estimation.ElectronicContractInfo.DesiredStartDateDiv;

@Converter(autoApply = true)
public class DesiredStartDateDivConverter implements AttributeConverter<DesiredStartDateDiv, String> {

	@Override
	public String convertToDatabaseColumn(DesiredStartDateDiv desiredStartDateDiv) {
		if (desiredStartDateDiv == null)
			return null;
		return desiredStartDateDiv.toString();
	}

	@Override
	public DesiredStartDateDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return DesiredStartDateDiv.fromString(value); //IllegalArgumentExceptionはContractSpanStartDateType.fromString側で投げている
	}
}