package jp.co.ricoh.cotos.commonlib.converter.estimation;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.estimation.SeOperationHistory.ProcessingCategory;

@Converter(autoApply = true)
public class ProcessingCategoryConverter implements AttributeConverter<ProcessingCategory, String> {
	@Override
	public String convertToDatabaseColumn(ProcessingCategory processingCategory) {
		if (processingCategory == null)
			return null;
		return processingCategory.toString();
	}

	@Override
	public ProcessingCategory convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ProcessingCategory.fromString(value); // IllegalArgumentExceptionはProcessingCategory.fromString側で投げている
	}
}
