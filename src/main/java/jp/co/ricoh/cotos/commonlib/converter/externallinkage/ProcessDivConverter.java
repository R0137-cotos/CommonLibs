package jp.co.ricoh.cotos.commonlib.converter.externallinkage;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.externallinkage.MobileEquipment.ProcessDiv;

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
		return ProcessDiv.fromString(value); // IllegalArgumentExceptionはfromString側で投げている
	}
}
