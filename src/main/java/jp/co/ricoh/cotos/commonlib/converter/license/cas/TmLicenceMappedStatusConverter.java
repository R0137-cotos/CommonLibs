package jp.co.ricoh.cotos.commonlib.converter.license.cas;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import jp.co.ricoh.cotos.commonlib.entity.license.tm.AbstractTmResponseWork.TmLicenceMappedStatus;

@Converter(autoApply = true)
public class TmLicenceMappedStatusConverter implements AttributeConverter<TmLicenceMappedStatus, String> {
	@Override
	public String convertToDatabaseColumn(TmLicenceMappedStatus tmLicenceMappedStatus) {
		if (tmLicenceMappedStatus == null)
			return null;
		return tmLicenceMappedStatus.toString();
	}

	@Override
	public TmLicenceMappedStatus convertToEntityAttribute(String value) {
		if (value == null)
			return null;
		return TmLicenceMappedStatus.fromString(value); // IllegalArgumentExceptionはContractType.fromString側で投げている
	}
}
