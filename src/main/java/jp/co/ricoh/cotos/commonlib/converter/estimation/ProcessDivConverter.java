package jp.co.ricoh.cotos.commonlib.converter.estimation;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.estimation.SeOperationHistory.ProcessDiv;

@Converter(autoApply = true)
public class ProcessDivConverter implements AttributeConverter<ProcessDiv, String> {

	@Override
	public String convertToDatabaseColumn(ProcessDiv processDiv) {
		if (processDiv == null)
			return null;
		return processDiv.toString();
	}

	@Override
	public ProcessDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ProcessDiv.fromString(value);
	}

}
