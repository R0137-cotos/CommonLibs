package jp.co.ricoh.cotos.commonlib.converter.master;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.master.LicenseServiceMaster.LicenseType;

@Converter(autoApply = true)
public class LicenseTypeConverter implements AttributeConverter<LicenseType, String> {
	@Override
	public String convertToDatabaseColumn(LicenseType licenseType) {
		if (licenseType == null)
			return null;
		return licenseType.toString();
	}

	@Override
	public LicenseType convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return LicenseType.fromString(value); //IllegalArgumentExceptionはLicenseType.fromString側で投げている
	}
}
