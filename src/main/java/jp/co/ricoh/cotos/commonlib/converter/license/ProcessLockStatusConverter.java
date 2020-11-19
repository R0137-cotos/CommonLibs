package jp.co.ricoh.cotos.commonlib.converter.license;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfo.ProcessLockStatus;

@Converter(autoApply = true)
public class ProcessLockStatusConverter implements AttributeConverter<ProcessLockStatus, String> {

	@Override
	public String convertToDatabaseColumn(ProcessLockStatus processLockStatus) {
		if (processLockStatus == null)
			return null;
		return processLockStatus.toString();
	}

	@Override
	public ProcessLockStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return ProcessLockStatus.fromString(value); // IllegalArgumentExceptionはInfoDiv.fromString側で投げている
	}
}
