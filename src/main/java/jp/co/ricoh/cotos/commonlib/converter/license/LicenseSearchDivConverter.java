package jp.co.ricoh.cotos.commonlib.converter.license;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.dto.parameter.license.LicenseSearchParameter.LicenseSearchDiv;

@Converter(autoApply = true)
public class LicenseSearchDivConverter implements AttributeConverter<LicenseSearchDiv, String> {

	@Override
	public String convertToDatabaseColumn(LicenseSearchDiv licenseSearchDiv) {
		if (licenseSearchDiv == null)
			return null;
		return licenseSearchDiv.toString();
	}

	@Override
	public LicenseSearchDiv convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return LicenseSearchDiv.fromString(value); // IllegalArgumentExceptionはInfoDiv.fromString側で投げている
	}
}
