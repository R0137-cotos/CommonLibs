package jp.co.ricoh.cotos.commonlib.converter.master;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.FileKindManagementMaster.ErrorProcessingType;

@Converter(autoApply = true)
public class ErrorProcessingTypeConverter implements AttributeConverter<ErrorProcessingType, String> {

	@Override
	public String convertToDatabaseColumn(ErrorProcessingType errorProcessingType) {
		if (errorProcessingType == null)
			return null;
		return errorProcessingType.toString();
	}

	@Override
	public ErrorProcessingType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ErrorProcessingType.fromString(value); //IllegalArgumentExceptionはErrorProcessingType.fromString側で投げている
	}
}