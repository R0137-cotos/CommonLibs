package jp.co.ricoh.cotos.commonlib.converter.license;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.license.LicenseDetail.InfoDiv;

@Converter(autoApply = true)
public class InfoDivConverter implements AttributeConverter<InfoDiv, String> {

	@Override
	public String convertToDatabaseColumn(InfoDiv infoDiv) {
		if (infoDiv == null)
			return null;
		return infoDiv.toString();
	}

	@Override
	public InfoDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return InfoDiv.fromString(value); // IllegalArgumentExceptionはInfoDiv.fromString側で投げている
	}

}
