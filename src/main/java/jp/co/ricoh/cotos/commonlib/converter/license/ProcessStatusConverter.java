package jp.co.ricoh.cotos.commonlib.converter.license;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.license.LicenseProcess.ProcessStatus;

@Converter(autoApply = true)
public class ProcessStatusConverter implements AttributeConverter<ProcessStatus, String> {

	@Override
	public String convertToDatabaseColumn(ProcessStatus processStatus) {
		if (processStatus == null)
			return null;
		return processStatus.toString();
	}

	@Override
	public ProcessStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ProcessStatus.fromString(value); // IllegalArgumentExceptionはInfoDiv.fromString側で投げている
	}
}
