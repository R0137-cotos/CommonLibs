package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.LaitLinkageInfo.InfoDiv;

@Converter(autoApply = true)
public class InfoDivConverter implements AttributeConverter<InfoDiv, String> {

	@Override
	public String convertToDatabaseColumn(InfoDiv InfoDiv) {
		if (InfoDiv == null)
			return null;
		return InfoDiv.toString();
	}

	@Override
	public InfoDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return InfoDiv.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
