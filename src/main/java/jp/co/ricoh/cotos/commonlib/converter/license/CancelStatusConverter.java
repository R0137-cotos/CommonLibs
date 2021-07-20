package jp.co.ricoh.cotos.commonlib.converter.license;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.license.LicenseInfo.CancelStatus;

@Converter(autoApply = true)
public class CancelStatusConverter implements AttributeConverter<CancelStatus, String> {

	@Override
	public String convertToDatabaseColumn(CancelStatus cancelStatus) {
		if (cancelStatus == null)
			return null;
		return cancelStatus.toString();
	}

	@Override
	public CancelStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return CancelStatus.fromString(value); // IllegalArgumentExceptionはInfoDiv.fromString側で投げている
	}
}
