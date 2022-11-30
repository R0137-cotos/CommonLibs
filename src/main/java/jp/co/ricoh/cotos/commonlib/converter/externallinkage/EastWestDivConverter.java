package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.ROpticalBillingWork.EastWestDiv;

@Converter(autoApply = true)
public class EastWestDivConverter implements AttributeConverter<EastWestDiv, String> {

	@Override
	public String convertToDatabaseColumn(EastWestDiv eastWestDiv) {
		if (eastWestDiv == null)
			return null;
		return eastWestDiv.toString();
	}

	@Override
	public EastWestDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return EastWestDiv.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
