package jp.co.ricoh.cotos.commonlib.converter.license.ms;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.license.ms.MsSubscriptionRegisterRequestWork.IncreaseDecreaseDiv;

@Converter(autoApply = true)
public class IncreaseDecreaseDivConverter implements AttributeConverter<IncreaseDecreaseDiv, String> {

	@Override
	public String convertToDatabaseColumn(IncreaseDecreaseDiv increaseDecreaseDiv) {
		if (increaseDecreaseDiv == null)
			return null;
		return increaseDecreaseDiv.toString();
	}

	@Override
	public IncreaseDecreaseDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return IncreaseDecreaseDiv.fromString(value); //IllegalArgumentExceptionはIncreaseDecreaseDiv.fromString側で投げている
	}

}
