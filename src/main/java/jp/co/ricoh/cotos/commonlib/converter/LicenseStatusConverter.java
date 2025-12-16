package jp.co.ricoh.cotos.commonlib.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.LicenseStatus;

@Converter(autoApply = true)
public class LicenseStatusConverter implements AttributeConverter<LicenseStatus, String> {
	@Override
	public String convertToDatabaseColumn(LicenseStatus workflowType) {
		if (workflowType == null)
			return null;
		return workflowType.toString();
	}

	@Override
	public LicenseStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return LicenseStatus.fromString(value); // IllegalArgumentExceptionはLicenseStatus.fromString側で投げている
	}
}
