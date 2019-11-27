package jp.co.ricoh.cotos.commonlib.converter.accounting;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.accounting.OsoRequestDetailData.ProcessingDiv;

@Converter(autoApply = true)
public class ProcessingDivConverter implements AttributeConverter<ProcessingDiv, String> {
	@Override
	public String convertToDatabaseColumn(ProcessingDiv processingDiv) {
		if (processingDiv == null)
			return null;
		return processingDiv.toString();
	}

	@Override
	public ProcessingDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ProcessingDiv.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
