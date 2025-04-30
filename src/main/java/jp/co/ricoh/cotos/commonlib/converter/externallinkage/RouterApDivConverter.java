package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.LaitLinkageInfo.RouterApDiv;

@Converter(autoApply = true)
public class RouterApDivConverter implements AttributeConverter<RouterApDiv, String> {

	@Override
	public String convertToDatabaseColumn(RouterApDiv RouterApDiv) {
		if (RouterApDiv == null)
			return null;
		return RouterApDiv.toString();
	}

	@Override
	public RouterApDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return RouterApDiv.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
