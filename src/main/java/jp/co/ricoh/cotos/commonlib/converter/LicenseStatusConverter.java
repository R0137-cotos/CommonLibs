package jp.co.ricoh.cotos.commonlib.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.EnumType.CasLicenseStatus;

@Converter(autoApply = true)
public class LicenseStatusConverter implements AttributeConverter<CasLicenseStatus, String> {
	@Override
	public String convertToDatabaseColumn(CasLicenseStatus workflowType) {
		if (workflowType == null)
			return null;
		return workflowType.toString();
	}

	@Override
	public CasLicenseStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return CasLicenseStatus.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
