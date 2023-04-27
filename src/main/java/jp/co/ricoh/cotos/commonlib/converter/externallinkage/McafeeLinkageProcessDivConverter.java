package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.McafeeLinkageInfo.McafeeLinkageProcessDiv;

@Converter(autoApply = true)
public class McafeeLinkageProcessDivConverter implements AttributeConverter<McafeeLinkageProcessDiv, String> {

	@Override
	public String convertToDatabaseColumn(McafeeLinkageProcessDiv processDiv) {
		if (processDiv == null)
			return null;
		return processDiv.toString();
	}

	@Override
	public McafeeLinkageProcessDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return McafeeLinkageProcessDiv.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
