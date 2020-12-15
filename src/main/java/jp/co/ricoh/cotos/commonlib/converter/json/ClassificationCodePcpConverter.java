package jp.co.ricoh.cotos.commonlib.converter.json;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.json.JsonEnumTypePcp.ClassificationCodePcp;

@Converter(autoApply = true)
public class ClassificationCodePcpConverter implements AttributeConverter<ClassificationCodePcp, String> {
	@Override
	public String convertToDatabaseColumn(ClassificationCodePcp classificationCodePcp) {
		if (classificationCodePcp == null)
			return null;
		return classificationCodePcp.toString();
	}

	@Override
	public ClassificationCodePcp convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ClassificationCodePcp.fromString(value);
	}
}
